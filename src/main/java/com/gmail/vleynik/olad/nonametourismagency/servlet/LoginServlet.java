package com.gmail.vleynik.olad.nonametourismagency.servlet;

import com.gmail.vleynik.olad.nonametourismagency.DAO.UserDAO;
import com.gmail.vleynik.olad.nonametourismagency.DAO.UserNotFoundException;
import com.gmail.vleynik.olad.nonametourismagency.DAO.entity.User;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        UserDAO userDAO = new UserDAO();
        User user;

        try {
            if (login.contains("@"))
                user = userDAO.getByEmail(login);
            else
                user = userDAO.getByPhoneNumber(login);

            if (password.equals(user.getPassword())) {
                HttpSession session = request.getSession(true);

                // сессия рвется при закрытии вкладки
                session.setMaxInactiveInterval(-1);

                //если юзер пытался зарегистрироваться, указав неверные данные
                session.invalidate();

                session.setAttribute("user_full_name", user.getName() + " " + user.getSurname());
                session.setAttribute("user_id", user.getId());
            }

            response.sendRedirect(request.getContextPath() + "/");
        } catch (UserNotFoundException e) {
            System.out.println("user not found"); //TODO
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }


    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession(false);


        if (session == null || session.getAttribute("user_id") == null || session.getAttribute("user_id").equals("")) {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            if ("exit".equals(action)) {
                session.invalidate();
            }
            response.sendRedirect(request.getContextPath() + "/");
        }
    }
}