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
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(true);

        Enumeration<String> attributes = session.getAttributeNames();

        while (attributes.hasMoreElements()) {
            session.removeAttribute(attributes.nextElement());
        }

        HashMap<String, String> inputErrors = new HashMap<>();
        User newUser;
        UserDAO userDAO;

        String phoneNumber = request.getParameter("phoneNumber");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");

        inputErrors.put("email_input_error", UserInputCheck.checkEmail(email));
        inputErrors.put("phone_number_input_error", UserInputCheck.checkPhoneNumber(phoneNumber));
        inputErrors.put("password_input_error", UserInputCheck.checkPassword(password));
        inputErrors.put("name_input_error", UserInputCheck.checkName(name));
        inputErrors.put("surname_input_error", UserInputCheck.checkName(surname));

        inputErrors.entrySet()
                .removeIf(
                        entry -> (""
                                .equals(entry.getValue())));

        if (inputErrors.isEmpty()) {
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
            session.setAttribute("user_id", newUser.getId());
            session.setAttribute("user_full_name", newUser.getName() + " " + newUser.getSurname());

            // сессия рвется при закрытии вкладки
            session.setMaxInactiveInterval(-1);

            response.sendRedirect(request.getContextPath() + "/");
        } else {
            for (Map.Entry<String, String> error : inputErrors.entrySet()) {
                session.setAttribute(error.getKey(), error.getValue());
            }
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
