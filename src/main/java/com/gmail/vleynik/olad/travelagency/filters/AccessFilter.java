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

//    private static final List<String> USER_PAGES;
//    private static final List<String> MANAGER_PAGES;
//    private static final List<String> ADMIN_PAGES;
//
//    static {
//        USER_PAGES = Arrays.asList("/order_tour", "/profile");
//
//        MANAGER_PAGES = Arrays.asList("/edit_order", "/users", "/orders", "/edit_user");
//        MANAGER_PAGES.addAll(USER_PAGES);
//
//        ADMIN_PAGES = Arrays.asList("/add_tour", "/edit_tour", "/ban");
//        ADMIN_PAGES.addAll(MANAGER_PAGES);
//    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain next) throws ServletException, IOException {

        if (request instanceof HttpServletRequest) {
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse resp = (HttpServletResponse) response;

            User.AccessLevel userAccessLevel = (User.AccessLevel) req.getSession().getAttribute("user_access_level");

            String requestedServlet = req.getServletPath();
//
//            if (ADMIN_PAGES.contains(requestedServlet) && userAccessLevel != User.AccessLevel.ADMINISTRATOR
//                    || MANAGER_PAGES.contains(req.getServletPath()) && userAccessLevel != User.AccessLevel.MANAGER
//                    || USER_PAGES.contains(req.getServletPath()) && userAccessLevel != User.AccessLevel.CLIENT) {
//                resp.sendError(403);
//            }
        }
        next.doFilter(request, response);
    }
}
