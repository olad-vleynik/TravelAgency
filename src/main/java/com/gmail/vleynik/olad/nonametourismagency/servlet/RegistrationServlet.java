package com.gmail.vleynik.olad.nonametourismagency.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegistrationServlet extends HttpServlet {
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

        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession(false);

        if ("exit".equals(action) && (session != null))
            session.removeAttribute("user_login");


        request.getRequestDispatcher("register.jsp").forward(request, response);
    }
}
