package com.gmail.vleynik.olad.nonametourismagency.utils;

import com.gmail.vleynik.olad.nonametourismagency.DAO.UserDAO;
import com.gmail.vleynik.olad.nonametourismagency.DAO.UserNotFoundException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserInputCheck {

    private static final int MIN_PASSWORD_LENGTH = 5;

    private static final String EMAIL_DUPLICATE = "такой e-mail уже зарегистрирован";
    private static final String EMAIL_INVALID = "некорректный e-mail";
    private static final String PHONE_NUMBER_DUPLICATE = "такой номер уже зарегистрирован";
    private static final String PHONE_NUMBER_INVALID = "некорректный номер (пример ввода: +380993332211, 0993332211)";
    private static final String PASSWORD_TOO_SHORT = "пароль слишком короткий (минимум " + MIN_PASSWORD_LENGTH + " символов)";
    private static final String NAME_INVALID = "некорректный ввод";

    private UserInputCheck() {
    }

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALID_NUMBER_REGEX =
            Pattern.compile("^(\\+38)?0(67|68|96|97|98|50|66|95|99|63|73|93)\\d{7}$");
    public static final Pattern VALID_NAME_REGEX =
            Pattern.compile("^[A-Z][a-z]+$");

    public static boolean isValidAndNotDublicate(String email, String phoneNumber, String password, String name, String surname) {
        return checkEmail(email).equals("") && checkPhoneNumber(phoneNumber).equals("") && checkPassword(password).equals("") && checkName(name).equals("") && checkName(surname).equals("");
    }

    public static String checkEmail(String email) {
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

    public static String checkPhoneNumber(String phoneNumber) {
        Matcher matcher = VALID_NUMBER_REGEX.matcher(phoneNumber);
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

    public static String checkPassword(String password) {
        if (password.length() < MIN_PASSWORD_LENGTH)
            return PASSWORD_TOO_SHORT;
        return "";
    }

    public static String checkName(String name) {
        Matcher matcher = VALID_NAME_REGEX.matcher(name);
        if (matcher.find()) {
            return "";
        } else {
            return NAME_INVALID;
        }
    }

    private static boolean notDuplicate(String checkable, String value) {
        UserDAO userDAO = new UserDAO();
        try {
            switch (checkable) {
                case "email":
                    userDAO.getByEmail(value);
                    break;
                case "phoneNumber":
                    userDAO.getByPhoneNumber(value);
                    break;
                default:
                    throw new IllegalArgumentException();
            }
            return false;
        } catch (UserNotFoundException e) {
            return true;
        }
    }
}
