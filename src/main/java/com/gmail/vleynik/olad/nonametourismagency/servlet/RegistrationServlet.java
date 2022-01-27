package com.gmail.vleynik.olad.nonametourismagency.servlet;

import com.gmail.vleynik.olad.nonametourismagency.DAO.UserDAO;
import com.gmail.vleynik.olad.nonametourismagency.DAO.entity.User;
import com.gmail.vleynik.olad.nonametourismagency.utils.UserInputCheck;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,  ServletException {
        User newUser;
        UserDAO userDAO;

        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String password = request.getParameter("password");

        if (UserInputCheck.isValid(email, phoneNumber, password)) {
            newUser = new User();
            userDAO = new UserDAO();
            newUser.setName(request.getParameter("name"));
            newUser.setSurname(request.getParameter("surname"));
            newUser.setPhoneNumber(phoneNumber);
            newUser.setEmail(email);
            newUser.setPassword(password);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                newUser.setBirthDay(sdf.parse(request.getParameter("birthday")));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            newUser.setId(userDAO.addNew(newUser));
            HttpSession session = request.getSession(true);
            session.setAttribute("user_id", newUser.getId());
            session.setAttribute("user_full_name", newUser.getName() + " " + newUser.getSurname());

            response.sendRedirect(request.getContextPath() + "/");
        } else {
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user_id") == null || session.getAttribute("user_id").equals("")) {
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/");
        }
    }
}
