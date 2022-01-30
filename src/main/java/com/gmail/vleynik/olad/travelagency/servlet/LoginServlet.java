package com.gmail.vleynik.olad.travelagency.servlet;

import com.gmail.vleynik.olad.travelagency.dao.UserDAO;
import com.gmail.vleynik.olad.travelagency.dao.UserNotFoundException;
import com.gmail.vleynik.olad.travelagency.dao.entity.User;

import java.io.IOException;
import java.net.UnknownHostException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final String USER_ID = "user_id";
    private static final String USER_FULL_NAME = "user_full_name";
    private static final String USER_ACCESS_LEVEL = "user_access_level";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";

    private static final String LOGIN_JSP = "login.jsp";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);

        UserDAO userDAO = new UserDAO();
        User user;

        try {
            if (login.contains("@"))
                user = userDAO.getByEmail(login);
            else
                user = userDAO.getByPhoneNumber(login);

            if (password.equals(user.getPassword())) {
                HttpSession session = request.getSession(true);

                session.setAttribute(USER_FULL_NAME, user.getName() + " " + user.getSurname());
                session.setAttribute(USER_ID, user.getId());
                session.setAttribute(USER_ACCESS_LEVEL, user.getAccessLevel());
            } else {
                request.getRequestDispatcher(LOGIN_JSP).forward(request, response);
            }
            response.sendRedirect(request.getContextPath() + "/");
        } catch (UserNotFoundException e) {
            System.out.println("user not found"); //TODO
            request.getRequestDispatcher(LOGIN_JSP).forward(request, response);
        }


    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute(USER_ID) == null || session.getAttribute(USER_ID).equals("")) {
            request.getRequestDispatcher(LOGIN_JSP).forward(request, response);
        } else {
            if (action.equals("exit")) {
                session.removeAttribute(USER_FULL_NAME);
                session.removeAttribute(USER_ID);
                session.removeAttribute(USER_ACCESS_LEVEL);
            }
            response.sendRedirect(request.getContextPath() + "/");
        }
    }
}