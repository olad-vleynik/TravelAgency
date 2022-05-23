package com.gmail.vleynik.olad.travelagency.filters;

import com.gmail.vleynik.olad.travelagency.dao.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebFilter("/*")
public class AccessFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain next) throws ServletException, IOException {

        if (request instanceof HttpServletRequest) {
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse resp = (HttpServletResponse) response;

            User.AccessLevel userAccessLevel = (User.AccessLevel) req.getSession().getAttribute("user_access_level");

            String requestedServlet = req.getServletPath();

            if (!AccessFilterPaths.ADMIN_PAGES.contains(requestedServlet)
                    || (userAccessLevel == User.AccessLevel.CLIENT && AccessFilterPaths.USER_PAGES.contains(requestedServlet))
                    || (userAccessLevel == User.AccessLevel.MANAGER && AccessFilterPaths.MANAGER_PAGES.contains(requestedServlet))
                    || (userAccessLevel == User.AccessLevel.ADMINISTRATOR && AccessFilterPaths.ADMIN_PAGES.contains(requestedServlet))) {
                next.doFilter(request, response);
            } else {
                resp.sendError(HttpServletResponse.SC_FORBIDDEN);
            }
        }
    }
}
