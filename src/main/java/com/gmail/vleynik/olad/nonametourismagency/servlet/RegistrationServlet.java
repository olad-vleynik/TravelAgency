package com.gmail.vleynik.olad.nonametourismagency.servlet;

import com.gmail.vleynik.olad.nonametourismagency.DAO.UserDAO;
import com.gmail.vleynik.olad.nonametourismagency.DAO.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User newUser = new User();
        UserDAO userDAO = new UserDAO();

        System.out.println(request.getParameter("name"));

        newUser.setName(request.getParameter("name"));
        newUser.setSurname(request.getParameter("surname"));
        newUser.setPhoneNumber(request.getParameter("number"));
        newUser.setEmail(request.getParameter("email"));
        newUser.setPassword(request.getParameter("password"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            newUser.setBirthDay(sdf.parse(request.getParameter("birthday")));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        newUser.setId(userDAO.addNew(newUser));

        HttpSession session = request.getSession(true);
        session.setAttribute("user_id", newUser.getId());

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
