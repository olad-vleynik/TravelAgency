package com.gmail.vleynik.olad.nonametourismagency.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserInputCheck {

    private static final int MIN_PASSWORD_LENGTH = 5;

    private static final String EMAIL_DUPLICATE = "такой e-mail уже зарегистрирован";
    private static final String EMAIL_INVALID = "некорректный e-mail";
    private static final String NUMBER_DUPLICATE = "такой номер уже зарегистрирован";
    private static final String NUMBER_INVALID = "некорректный номер (пример ввода: +380993332211, 0993332211)";
    private static final String PASSWORD_TOO_SHORT = "пароль слишком короткий (минимум " + MIN_PASSWORD_LENGTH + " символов)";

    private static final String SELECT_USER_EMAIL_QUERY = "SELECT * FROM users WHERE email=?";
    private static final String SELECT_USER_NUMBER_QUERY = "SELECT * FROM users WHERE number=?";

    private UserInputCheck() {
    }

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALID_NUMBER_REGEX =
            Pattern.compile("^(\\+38)?(067|096|097|098|050|066|095|099|063|073|093)\\d{7}$");

    public static String checkEmail(String email) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        if (matcher.find()) {
            if (notDuplicate(SELECT_USER_EMAIL_QUERY, email)) {
                return "";
            } else {
                return EMAIL_DUPLICATE;
            }
        } else {
            return EMAIL_INVALID;
        }
    }

    public static String checkNumber(String number) {
        Matcher matcher = VALID_NUMBER_REGEX.matcher(number);
        if (matcher.find()) {
            if (number.startsWith("0"))
                number = "+38" + number;
            if (notDuplicate(SELECT_USER_NUMBER_QUERY, number))
                return "";
            else
                return NUMBER_DUPLICATE;
        } else {
            return NUMBER_INVALID;
        }
    }

    public static String checkPassword(String password) {
        if (password.length() < MIN_PASSWORD_LENGTH)
            return PASSWORD_TOO_SHORT;
        return "";
    }

    private static boolean notDuplicate(String statement, String checkable) {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(statement)) {

            preparedStatement.setString(1, checkable);
            ResultSet rs = preparedStatement.executeQuery();
            if (!rs.isBeforeFirst())
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
