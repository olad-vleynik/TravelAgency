package com.gmail.vleynik.olad.nonametourismagency.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ContextListener implements ServletContextListener {
    private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/testclients";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "admin";

    private static final String DROP_IF_EXISTS = "DROP TABLE IF EXISTS ";
    private static final String CREATE_CLIENTS = "CREATE TABLE Clients (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, name VARCHAR(128) NOT NULL, " +
            "surname VARCHAR(128) NOT NULL, phoneNumber VARCHAR(12) NOT NULL, email VARCHAR(128) NOT NULL, password VARCHAR(128) NOT NULL, " +
            "birthDay DATE DEFAULT NULL, balanceInUSD DOUBLE DEFAULT NULL, personalDiscount DOUBLE DEFAULT NULL, maxDiscount DOUBLE DEFAULT NULL, " +
            "isBanned BOOL DEFAULT FALSE)";

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try (Connection connection = getSQLConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(DROP_IF_EXISTS + "Clients");
            statement.execute(CREATE_CLIENTS);
            //statement.execute("INSERT INTO Clients VALUES");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getSQLConnection() throws SQLException {
        return DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
    }
}
