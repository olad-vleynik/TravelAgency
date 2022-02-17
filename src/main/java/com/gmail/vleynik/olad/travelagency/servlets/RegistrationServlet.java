package com.gmail.vleynik.olad.travelagency.servlets;

import com.gmail.vleynik.olad.travelagency.dao.UserDAO;
import com.gmail.vleynik.olad.travelagency.dao.entity.User;
import com.gmail.vleynik.olad.travelagency.dao.builders.UserBuilder;
import com.gmail.vleynik.olad.travelagency.utils.PasswordUtil;
import com.gmail.vleynik.olad.travelagency.utils.UserInputCheck;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(RegistrationServlet.class);

    private static final String USER_ID = "user_id";
    private static final String USER_FULL_NAME = "user_full_name";
    private static final String USER_ACCESS_LEVEL = "user_access_level";
    private static final String USER_BALANCE = "user_balance";

    private static final String ENTRY_JSP = "/WEB-INF/jsp/entry.jsp";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(true);
        User newUser;
        UserDAO userDAO;

        String phoneNumber = request.getParameter("phoneNumber");
        if (phoneNumber.startsWith("0"))
            phoneNumber = "+38" + phoneNumber;
        String password = request.getParameter("regPassword");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email").toLowerCase();
        String birthday = request.getParameter("birthday");
        try {
            if (UserInputCheck.isValidAndNotDuplicate(email, phoneNumber, password, name, surname)) {
                userDAO = new UserDAO();
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");

                byte[] passwordSalt = PasswordUtil.getSalt();
                String hashedPassword = PasswordUtil.getHash(password, passwordSalt);
                String saltAndPassword = Base64.getEncoder().encodeToString(passwordSalt) + hashedPassword;

                newUser = new UserBuilder(-2, name, surname, phoneNumber, email, saltAndPassword, LocalDate.parse(birthday, dateFormatter))
                        .build();

                newUser.setId(userDAO.addNew(newUser));

                session.setAttribute(USER_ID, newUser.getId());
                session.setAttribute(USER_ACCESS_LEVEL, newUser.getAccessLevel());
                session.setAttribute(USER_FULL_NAME, newUser.getName() + " " + newUser.getSurname());
                session.setAttribute(USER_BALANCE, 0);

                response.sendRedirect(request.getContextPath() + "/");
            } else {
                request.setAttribute("action", "registration");
                request.setAttribute("registrationErrorMessage", "duplicate.email.or.phone");
                request.getRequestDispatcher(ENTRY_JSP).forward(request, response);
            }
        } catch (SQLException e) {
            log.fatal("something wrong with database");
            response.sendError(503);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute(USER_ID) == null || session.getAttribute(USER_ID).equals("")) {
            request.setAttribute("action", "registration");
            request.getRequestDispatcher(ENTRY_JSP).forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/");
        }
    }
}
