package com.gmail.vleynik.olad.travelagency.servlet;

import com.gmail.vleynik.olad.travelagency.DAO.UserDAO;
import com.gmail.vleynik.olad.travelagency.DAO.entity.User;
import com.gmail.vleynik.olad.travelagency.utils.UserInputCheck;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(true);
        User newUser;
        UserDAO userDAO;

        String phoneNumber = request.getParameter("phoneNumber");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");


        if (UserInputCheck.isValidAndNotDuplicate(email, phoneNumber, password, name, surname)) {
            newUser = new User();
            userDAO = new UserDAO();
            newUser.setName(name);
            newUser.setSurname(surname);
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
            session.setAttribute("user_id", newUser.getId());
            session.setAttribute("user_full_name", newUser.getName() + " " + newUser.getSurname());

            // сессия рвется при закрытии вкладки
            session.setMaxInactiveInterval(-1);

            response.sendRedirect(request.getContextPath() + "/");
        } else {
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(true);
        if (session.getAttribute("user_id") == null || session.getAttribute("user_id").equals("")) {
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/");
        }
    }
}
