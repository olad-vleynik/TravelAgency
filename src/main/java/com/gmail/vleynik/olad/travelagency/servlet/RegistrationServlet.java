package com.gmail.vleynik.olad.travelagency.servlet;

import com.gmail.vleynik.olad.travelagency.dao.UserDAO;
import com.gmail.vleynik.olad.travelagency.dao.entity.User;
import com.gmail.vleynik.olad.travelagency.utils.UserInputCheck;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
    private static final String USER_ID = "user_id";
    private static final String USER_FULL_NAME = "user_full_name";
    private static final String USER_ACCESS_LEVEL = "user_access_level";

    private static final String REGISTER_JSP = "register.jsp";

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


        try {
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

                session.setAttribute(USER_ID, newUser.getId());
                session.setAttribute(USER_ACCESS_LEVEL, newUser.getAccessLevel());
                session.setAttribute(USER_FULL_NAME, newUser.getName() + " " + newUser.getSurname());

                response.sendRedirect(request.getContextPath() + "/");
            } else {
                request.getRequestDispatcher(REGISTER_JSP).forward(request, response);
            }
        } catch (SQLException e) {
            //TODO logger
            response.sendError(503);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(true);
        if (session.getAttribute(USER_ID) == null || session.getAttribute(USER_ID).equals("")) {
            request.getRequestDispatcher(REGISTER_JSP).forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/");
        }
    }
}
