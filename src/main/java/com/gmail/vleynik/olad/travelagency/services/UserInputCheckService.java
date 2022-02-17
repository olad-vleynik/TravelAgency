package com.gmail.vleynik.olad.travelagency.services;

import com.gmail.vleynik.olad.travelagency.dao.UserDAO;
import com.gmail.vleynik.olad.travelagency.dao.entity.User;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class used to check user input data during registration
 *
 * @author Vladyslav Oliinyk.
 * @version 1.0
 */
public class UserInputCheckService {

    private static final int MIN_PASSWORD_LENGTH = 5;
    
    /**
     * Private constructor excludes creation of utility class outside
     *
     * @throws IllegalStateException on calling
     */
    private UserInputCheckService() {
        throw new IllegalStateException("Utility class");
    }

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[a-zA-Z0-9]+[._-]?[a-zA-Z0-9]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
    public static final Pattern VALID_PHONE_NUMBER_REGEX =
            Pattern.compile("^(\\+38)?0(67|68|96|97|98|50|66|95|99|63|73|93)\\d{7}$");
    public static final Pattern VALID_NAME_REGEX =
            Pattern.compile("^(([А-Я][а-я]{1,39})|([A-Z][a-z]{1,39}))$");

    /**
     * Method validates input data by specific rules and checks uniqueness of phone number and e-mail
     *
     * @param email       - email check in {@link UserInputCheckService#validEmail}
     * @param phoneNumber - phone check in {@link UserInputCheckService#validPhoneNumber}
     * @param password    - password check in {@link UserInputCheckService#validPassword}
     * @param name        - name check in {@link UserInputCheckService#validName}
     * @param surname     - surname check in {@link UserInputCheckService#validName}
     * @return true if entered data is correct and unique, or false if something is not correct or not unique
     */
    public static boolean isValidAndNotDuplicate(String email, String phoneNumber, String password, String name, String surname) throws SQLException {
        return validEmail(email) && validPhoneNumber(phoneNumber)
                && validPassword(password) && validName(name) && validName(surname);
    }

    /**
     * Method checks that all given parameters are not null
     *
     * @param objects - array of any objects
     * @return true if all objects are not null, or false if one or more are null
     */
    public static boolean isAllFilled(Object... objects) {
        for (Object object : objects) {
            if (object == null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Method checks that e-mail matches regex {@link UserInputCheckService#VALID_EMAIL_ADDRESS_REGEX}
     * and user with such e-mail is not present in the database
     *
     * @param email - email to check
     * @return true if e-mail is valid and unique, or false if not
     */
    public static boolean validEmail(String email) throws SQLException {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);

        return email.length() <= 40 && matcher.find() && notDuplicate("email", email);
    }

    /**
     * Method checks that phone number matches regex {@link UserInputCheckService#VALID_PHONE_NUMBER_REGEX}
     * and user with such phone number is not present in the database
     *
     * @param phoneNumber - phone number to check
     * @return  true if phone number is valid and unique, or false if not
     */
    public static boolean validPhoneNumber(String phoneNumber) throws SQLException {
        Matcher matcher = VALID_PHONE_NUMBER_REGEX.matcher(phoneNumber);

        return matcher.find() && notDuplicate("phoneNumber", phoneNumber);
    }

    /**
     * Method checks that password is not shorter than {@link UserInputCheckService#MIN_PASSWORD_LENGTH}
     *
     * @param password - password to check
     * @return true if password is correct, or password is too short
     */
    public static boolean validPassword(String password) {
        return password.length() >= MIN_PASSWORD_LENGTH;
    }

    /**
     * Method checks that name (or surname) starts with capital letter, and name length is longer than 2 symbols,
     * regex - {@link UserInputCheckService#VALID_NAME_REGEX}
     *
     * @param name - name or surname to check
     * @return true if name (or surname) is correct, or false if not
     */
    public static boolean validName(String name) {
        Matcher matcher = VALID_NAME_REGEX.matcher(name);

        return matcher.find();
    }

    /**
     * Method is email or phone number unique in database
     *
     * @param checkable - value needed to check
     * @param value     - type of checkable value
     *                  ("email" or "phoneNumber", otherwise {@link IllegalArgumentException} will be thrown)
     * @return true if checked value is unique in database, or false if not
     * @throws IllegalArgumentException if method called with incorrect "value" parameter (not "email" or "phoneNumber")
     */
    private static boolean notDuplicate(String checkable, String value) throws SQLException {
        User user;
        UserDAO userDAO = new UserDAO();
        switch (checkable) {
            case "email":
                user = userDAO.getByEmail(value);
                break;
            case "phoneNumber":
                user = userDAO.getByPhoneNumber(value);
                break;
            default:
                throw new IllegalArgumentException();
        }
        return user.getId() == -1;
    }
}
