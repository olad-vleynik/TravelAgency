package com.gmail.vleynik.olad.nonametourismagency.DAO;

import com.gmail.vleynik.olad.nonametourismagency.DAO.entity.User;
import com.gmail.vleynik.olad.nonametourismagency.utils.ConnectionUtil;

import java.sql.*;

public class UserDAO implements DAO<User> {

    private static final String INSERT_USER_QUERY =
            "INSERT INTO users VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT)";
    private static final String SELECT_USER_QUERY = "SELECT * FROM users WHERE id=?";
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
    public User get(int id) {
        User user = new User();
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_QUERY)) {

            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();

            user = new User(rs.getInt("id"),  rs.getString("name"),  rs.getString("surname"),
                    rs.getString("phoneNumber"),  rs.getString("email"),  rs.getString("password"),
                    rs.getDate("birtDay"),  rs.getDouble("balanceInUSD"),  rs.getDouble("personalDiscount"),
                    rs.getDouble("maxDiscount"), rs.getBoolean("isBanned"), User.AccessLevel.valueOf(rs.getString("accessLevel")));
        } catch (SQLException e) {
            e.printStackTrace();
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
