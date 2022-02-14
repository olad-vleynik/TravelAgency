package com.gmail.vleynik.olad.travelagency.servlets;

import com.gmail.vleynik.olad.travelagency.dao.SavedEntryDAO;
import com.gmail.vleynik.olad.travelagency.dao.UserDAO;
import com.gmail.vleynik.olad.travelagency.dao.entity.SavedEntry;
import com.gmail.vleynik.olad.travelagency.dao.entity.User;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import com.gmail.vleynik.olad.travelagency.utils.PasswordUtil;
import org.apache.log4j.Logger;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(LoginServlet.class);

    private static final String USER_ID = "user_id";
    private static final String USER_FULL_NAME = "user_full_name";
    private static final String USER_ACCESS_LEVEL = "user_access_level";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";

    private static final String ENTRY_JSP = "/WEB-INF/jsp/entry.jsp";

    private static final int COOKIE_AGE_IN_DAYS = 30;
    private static final int COOKIE_AGE_IN_SECONDS = 60 * 60 * 24 * COOKIE_AGE_IN_DAYS;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);

        UserDAO userDAO = new UserDAO();
        User user;

        try {
            if (login.contains("@"))
                user = userDAO.getByEmail(login.toLowerCase());
            else
                user = userDAO.getByPhoneNumber(login);

            if (user.getId() != -1 && PasswordUtil.isPasswordCorrect(user.getPassword(), password) ) {
                HttpSession session = request.getSession(true);

                session.setAttribute(USER_FULL_NAME, user.getName() + " " + user.getSurname());
                session.setAttribute(USER_ID, user.getId());
                session.setAttribute(USER_ACCESS_LEVEL, user.getAccessLevel());

                if (request.getParameter("remember") != null){
                    SavedEntryDAO savedEntryDAO = new SavedEntryDAO();
                    String uniqueId = savedEntryDAO.getUniqueUUID();

                    Cookie uuidCookie = new Cookie("uniqueId", uniqueId);
                    uuidCookie.setPath("/");
                    uuidCookie.setDomain(request.getServerName());
                    uuidCookie.setMaxAge(COOKIE_AGE_IN_SECONDS);
                    response.addCookie(uuidCookie);

                    savedEntryDAO.addNew(new SavedEntry(uniqueId, user.getId(), LocalDate.now().plusDays(COOKIE_AGE_IN_DAYS)));
                }

                response.sendRedirect(request.getContextPath() + "/");
            } else {
                log.warn("client " + request.getRemoteAddr() + " login fail. Login: " + login + " Reason: Incorrect password");
                request.setAttribute("loginErrorMessage", "incorrect.login.password");
                request.getRequestDispatcher(ENTRY_JSP).forward(request, response);
            }
        } catch (SQLException e) {
            log.fatal("something wrong with database");
            response.sendError(503);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute(USER_ID) == null || session.getAttribute(USER_ID).equals("") || action == null) {
            request.setAttribute("action", "login");
            request.getRequestDispatcher(ENTRY_JSP).forward(request, response);
        } else {
            if (action.equals("exit")) {
                String uuid = getUserUUID(request);

                if (uuid != null) {
                    SavedEntryDAO savedEntryDAO = new SavedEntryDAO();
                    savedEntryDAO.deleteByUUID(uuid);

                    Cookie uuidCookie = new Cookie("uniqueId", "");
                    uuidCookie.setPath("/");
                    uuidCookie.setMaxAge(0);
                    response.addCookie(uuidCookie);
                }

                session.removeAttribute(USER_FULL_NAME);
                session.removeAttribute(USER_ID);
                session.removeAttribute(USER_ACCESS_LEVEL);
            }
            response.sendRedirect(request.getContextPath() + "/");
        }
    }

    private String getUserUUID(HttpServletRequest request){
        String uuid = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("uniqueId".equals(cookie.getName())) {
                    uuid = cookie.getValue();
                    break;
                }
            }
        }
        return uuid;
    }
}