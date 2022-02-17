package com.gmail.vleynik.olad.travelagency.dao;

import com.gmail.vleynik.olad.travelagency.dao.builders.UserBuilder;
import com.gmail.vleynik.olad.travelagency.dao.entity.User;
import com.gmail.vleynik.olad.travelagency.services.ConnectionService;

import java.sql.*;
import java.util.Locale;

public class UserDAO {

    private static final String INSERT_USER_QUERY =
            "INSERT INTO users VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT)";
    public static final String SELECT_USER_BY_ID = "SELECT users.*, access_levels.name AS accessLevel FROM users "
            + "LEFT JOIN access_levels ON accessLevelId=access_levels.id WHERE users.id=?";
    private static final String SELECT_BY_PHONE_NUMBER_USER_QUERY = "SELECT users.*, access_levels.name AS accessLevel "
            + "FROM users LEFT JOIN access_levels ON accessLevelId=access_levels.id WHERE phoneNumber=?";
    private static final String SELECT_BY_EMAIL_USER_QUERY = "SELECT users.*, access_levels.name AS accessLevel "
            + "FROM users LEFT JOIN access_levels ON accessLevelId=access_levels.id WHERE email=?";
    private static final String DELETE_USER_QUERY = "DELETE FROM users WHERE id=?";

    public int addNew(User user) {
        try (Connection connection = ConnectionService.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_QUERY, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getPhoneNumber());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setDate(6, Date.valueOf(user.getBirthDay()));

            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();

            return (int) generatedKeys.getLong(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public User getById(int id) throws SQLException {
        return getUser(SELECT_USER_BY_ID, String.valueOf(id), true);
    }

    public User getByPhoneNumber(String phoneNumber) throws SQLException {
        if (phoneNumber.startsWith("0"))
            phoneNumber = "+38" + phoneNumber;
        return getUser(SELECT_BY_PHONE_NUMBER_USER_QUERY, phoneNumber, false);
    }

    public User getByEmail(String email) throws SQLException {
        return getUser(SELECT_BY_EMAIL_USER_QUERY, email, false);
    }

    private User getUser(String query, String value, boolean isValueInt) throws SQLException {
        User user = new User();
        try (Connection connection = ConnectionService.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {


            if (isValueInt)
                preparedStatement.setInt(1, Integer.parseInt(value));
            else
                preparedStatement.setString(1, value);

            ResultSet rs = preparedStatement.executeQuery();
            if (!rs.isBeforeFirst()) {
                user.setId(-1);
                return user;
            }

            rs.next();

            user = new UserBuilder(rs.getInt("id"), rs.getString("name"),
                    rs.getString("surname"), rs.getString("phoneNumber"),
                    rs.getString("email"), rs.getString("password"), rs.getDate("birthDay").toLocalDate())
                    .setBalanceInUSD(rs.getDouble("balanceInUSD"))
                    .setPersonalDiscount(rs.getDouble("personalDiscount"))
                    .setMaxDiscount(rs.getDouble("maxDiscount"))
                    .setBanned(rs.getBoolean("isBanned"))
                    .setAccessLevel(User.AccessLevel.valueOf(rs.getString("accessLevel").toUpperCase(Locale.ROOT)))
                    .build();
        }
        return user;
    }

    public void update(User user) {

    }

    public void delete(int id) {
        try (Connection connection = ConnectionService.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_QUERY)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
