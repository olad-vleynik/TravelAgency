package com.gmail.vleynik.olad.travelagency.servlet;

import com.gmail.vleynik.olad.travelagency.dao.UserDAO;
import com.gmail.vleynik.olad.travelagency.dao.entity.User;
import com.gmail.vleynik.olad.travelagency.utils.PasswordHashUtil;
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
import java.util.Base64;
import java.util.Locale;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
    private static final String USER_ID = "user_id";
    private static final String USER_FULL_NAME = "user_full_name";
    private static final String USER_ACCESS_LEVEL = "user_access_level";

    private static final String ENTRY_JSP = "entry.jsp";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(true);
        User newUser;
        UserDAO userDAO;

        String phoneNumber = request.getParameter("phoneNumber");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email").toLowerCase();
        String birthday = request.getParameter("birthday");

        try {
            if (UserInputCheck.isValidAndNotDuplicate(email, phoneNumber, password, name, surname)) {
                newUser = new User();
                userDAO = new UserDAO();
                newUser.setName(name);
                newUser.setSurname(surname);
                newUser.setPhoneNumber(phoneNumber);
                newUser.setEmail(email);

                byte[] passwordSalt = PasswordHashUtil.getSalt();
                String hashedPassword = PasswordHashUtil.getHash(password, passwordSalt);
                newUser.setPassword(Base64.getEncoder().encodeToString(passwordSalt) + hashedPassword);

                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                try {
                    newUser.setBirthDay(sdf.parse(birthday));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                newUser.setId(userDAO.addNew(newUser));

                session.setAttribute(USER_ID, newUser.getId());
                session.setAttribute(USER_ACCESS_LEVEL, newUser.getAccessLevel());
                session.setAttribute(USER_FULL_NAME, newUser.getName() + " " + newUser.getSurname());

                response.sendRedirect(request.getContextPath() + "/");
            } else {
                request.getRequestDispatcher(ENTRY_JSP).forward(request, response);
                //TODO ошибка в форме, вместо forward
            }
        } catch (SQLException e) {
            //TODO logger
            response.sendError(503);
        } catch (RuntimeException e) {
            //TODO logger
            response.sendError(503);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("omg");
    }
}
