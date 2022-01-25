package com.gmail.vleynik.olad.nonametourismagency.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String login = request.getParameter("email");
        String password = request.getParameter("password");

        if (login.equals(login) && password.equals(password)) {
            HttpSession session = request.getSession(true);
            session.setAttribute("user_login", login);
        }
        response.sendRedirect(request.getContextPath() + "/");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user_login") == null || session.getAttribute("user_login").equals("")) {
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/");
        }
    }
}
