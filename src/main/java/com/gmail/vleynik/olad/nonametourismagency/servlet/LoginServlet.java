package com.gmail.vleynik.olad.nonametourismagency.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

//@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    static final String LOGIN = "admin";
    static final String PASS = "admin";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (LOGIN.equals(login) && PASS.equals(password)) {
            HttpSession session = request.getSession(true);
            session.setAttribute("user_login", login);
        }

        response.sendRedirect(request.getContextPath() + "/");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession(false);


        if (session == null || session.getAttribute("user_login") == null || session.getAttribute("user_login").equals("")) {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            if ("exit".equals(action)) {
                session.removeAttribute("user_login");
            }
            response.sendRedirect(request.getContextPath() + "/");
        }
    }
}