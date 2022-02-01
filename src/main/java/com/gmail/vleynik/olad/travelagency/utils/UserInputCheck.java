package com.gmail.vleynik.olad.travelagency.utils;

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
public class UserInputCheck {

    private static final int MIN_PASSWORD_LENGTH = 5;

    private static final String EMAIL_DUPLICATE = "такой e-mail уже зарегистрирован";
    private static final String EMAIL_INVALID = "некорректный e-mail";
    private static final String PHONE_NUMBER_DUPLICATE = "такой номер уже зарегистрирован";
    private static final String PHONE_NUMBER_INVALID = "некорректный номер (пример ввода: +380993332211, 0993332211)";
    private static final String PASSWORD_TOO_SHORT = "пароль слишком короткий (минимум " + MIN_PASSWORD_LENGTH + " символов)";
    private static final String NAME_INVALID = "некорректный ввод";

    /**
     * Private constructor excludes creation of utility class outside
     *
     * @throws IllegalStateException on calling
     */
    private UserInputCheck() {
        throw new IllegalStateException("Utility class");
    }

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALID_PHONE_NUMBER_REGEX =
            Pattern.compile("^(\\+38)?0(67|68|96|97|98|50|66|95|99|63|73|93)\\d{7}$");
    public static final Pattern VALID_NAME_REGEX =
            Pattern.compile("^[A-Z][a-z]+$");

    /**
     * Method validates input data by specific rules and checks uniqueness of phone number and e-mail
     *
     * @param email       - email check in {@link UserInputCheck#checkEmail}
     * @param phoneNumber - phone check in {@link UserInputCheck#checkPhoneNumber}
     * @param password    - password check in {@link UserInputCheck#checkPassword}
     * @param name        - name check in {@link UserInputCheck#checkName}
     * @param surname     - surname check in {@link UserInputCheck#checkName}
     * @return true if entered data is correct and unique, or false if something is not correct or not unique
     */
    public static boolean isValidAndNotDuplicate(String email, String phoneNumber, String password, String name, String surname) throws SQLException {
        return checkEmail(email).equals("") && checkPhoneNumber(phoneNumber).equals("")
                && checkPassword(password).equals("") && checkName(name).equals("") && checkName(surname).equals("");
    }

    /**
     * Method checks that e-mail matches regex {@link UserInputCheck#VALID_EMAIL_ADDRESS_REGEX}
     * and user with such e-mail is not present in the database
     *
     * @param email - email to check
     * @return "" if e-mail is valid and unique, or
     * {@link UserInputCheck#EMAIL_DUPLICATE} if user with such e=mail already exists, or
     * {@link UserInputCheck#EMAIL_INVALID} if entered e-mail is incorrect
     */
    public static String checkEmail(String email) throws SQLException {
        if (email == null)
            return "";
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        if (matcher.find()) {
            if (notDuplicate("email", email)) {
                return "";
            } else {
                return EMAIL_DUPLICATE;
            }
        } else {
            return EMAIL_INVALID;
        }
    }

    /**
     * Method checks that phone number matches regex {@link UserInputCheck#VALID_PHONE_NUMBER_REGEX}
     * and user with such phone number is not present in the database
     *
     * @param phoneNumber - phone number to check
     * @return "" if phone number is valid and unique, or
     * {@link UserInputCheck#PHONE_NUMBER_DUPLICATE} if user with such phone number already exists, or
     * {@link UserInputCheck#PHONE_NUMBER_INVALID} if entered phone number is incorrect
     */
    public static String checkPhoneNumber(String phoneNumber) throws SQLException {
        if (phoneNumber == null)
            return "";
        Matcher matcher = VALID_PHONE_NUMBER_REGEX.matcher(phoneNumber);
        if (matcher.find()) {
            if (phoneNumber.startsWith("0"))
                phoneNumber = "+38" + phoneNumber;
            if (notDuplicate("phoneNumber", phoneNumber))
                return "";
            else
                return PHONE_NUMBER_DUPLICATE;
        } else {
            return PHONE_NUMBER_INVALID;
        }
    }

    /**
     * Method checks that password is not shorter than {@link UserInputCheck#MIN_PASSWORD_LENGTH}
     *
     * @param password - password to check
     * @return "" if password is correct, or
     * {@link UserInputCheck#PASSWORD_TOO_SHORT} if password is too short
     */
    public static String checkPassword(String password) {
        if (password == null || password.length() >= MIN_PASSWORD_LENGTH)
            return "";
        return PASSWORD_TOO_SHORT;
    }

    /**
     * Method checks that name (or surname) starts with capital letter, and name length is longer than 2 symbols,
     * regex - {@link UserInputCheck#VALID_NAME_REGEX}
     *
     * @param name - name or surname to check
     * @return "" if name (or surname) is correct, or
     * {@link UserInputCheck#NAME_INVALID} if incorrect
     */
    public static String checkName(String name) {
        if (name == null || VALID_NAME_REGEX.matcher(name).find()) {
            return "";
        }
        return NAME_INVALID;
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
