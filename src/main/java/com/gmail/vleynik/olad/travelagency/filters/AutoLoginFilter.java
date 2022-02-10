package com.gmail.vleynik.olad.travelagency.filters;

import com.gmail.vleynik.olad.travelagency.dao.SavedEntryDAO;
import com.gmail.vleynik.olad.travelagency.dao.UserDAO;
import com.gmail.vleynik.olad.travelagency.dao.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Used to auto login client, if it has cookie with uuid and this uuid presents in "saved_entries" database table
 *
 * @author Vladyslav Oliinyk.
 * @version 1.0
 */
@WebFilter("/*")
public class AutoLoginFilter implements Filter {

    private static final Logger log = Logger.getLogger(AutoLoginFilter.class);

    //30 days
    private static final int COOKIE_AGE = 60 * 60 * 24 * 30;

    private static final String USER_ID = "user_id";
    private static final String USER_FULL_NAME = "user_full_name";
    private static final String USER_ACCESS_LEVEL = "user_access_level";

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain next)
            throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) request).getSession();

        if (session.isNew()) {
            int userId = -1;
            String uuid = "-1";

            Cookie[] cookies = ((HttpServletRequest) request).getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("uniqueId".equals(cookie.getName())) {
                        SavedEntryDAO savedEntryDAO = new SavedEntryDAO();
                        uuid = cookie.getValue();
                        userId = savedEntryDAO.getByUUID(uuid).getUserId();
                        break;
                    }
                }
            }

            if (userId != -1) {
                try {
                    UserDAO userDAO = new UserDAO();
                    User user = userDAO.getById(userId);

                    session.setAttribute(USER_FULL_NAME, user.getName() + " " + user.getSurname());
                    session.setAttribute(USER_ID, user.getId());
                    session.setAttribute(USER_ACCESS_LEVEL, user.getAccessLevel());

                    Cookie localeCookie = new Cookie("uniqueId", uuid);
                    localeCookie.setPath("/");
                    localeCookie.setDomain(request.getServerName());
                    localeCookie.setMaxAge(COOKIE_AGE);
                    ((HttpServletResponse)response).addCookie(localeCookie);
                } catch (SQLException e) {
                    log.fatal("can't connect to database");
                }
            }
        }

        next.doFilter(request, response);
    }
}
