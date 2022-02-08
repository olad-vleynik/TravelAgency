package com.gmail.vleynik.olad.travelagency.dao;

import com.gmail.vleynik.olad.travelagency.dao.entity.User;
import com.gmail.vleynik.olad.travelagency.dao.entity.UserBuilder;
import com.gmail.vleynik.olad.travelagency.utils.ConnectionUtil;

import java.sql.*;

public class UserDAO implements DAO<User> {

    private static final String INSERT_USER_QUERY =
            "INSERT INTO users VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT)";
    private static final String SELECT_BY_ID_USER_QUERY = "SELECT * FROM users WHERE id=?";
    private static final String SELECT_BY_PHONE_NUMBER_USER_QUERY = "SELECT * FROM users WHERE phoneNumber=?";
    private static final String SELECT_BY_EMAIL_USER_QUERY = "SELECT * FROM users WHERE email=?";
    private static final String DELETE_USER_QUERY = "DELETE FROM users WHERE id=?";

    @Override
    public int addNew(User user) {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_QUERY, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getPhoneNumber());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setDate(6, new java.sql.Date(user.getBirthDay().getTime()));

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public User getById(int id) throws SQLException {
        return getUser(SELECT_BY_ID_USER_QUERY, String.valueOf(id), true);
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
        try (Connection connection = ConnectionUtil.getConnection();
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
                    rs.getString("email"), rs.getString("password"), rs.getDate("birthDay"))
                    .setBalanceInUSD(rs.getDouble("balanceInUSD"))
                    .setPersonalDiscount(rs.getDouble("personalDiscount"))
                    .setMaxDiscount(rs.getDouble("maxDiscount"))
                    .setBanned(rs.getBoolean("isBanned"))
                    .setAccessLevel(User.AccessLevel.valueOf(rs.getString("accessLevel")))
                    .build();
        }
        return user;
    }

    @Override
    public void update(User user, String[] params) {

    }

    @Override
    public void delete(int id) {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_QUERY)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
