package com.gmail.vleynik.olad.nonametourismagency.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private static final String PROPERTIES_FILE = "database.properties";
    private static String connectionURL;
    private static String user;
    private static String password;
    private static String driver;

    static {
        try {
            Properties dataBaseProperties = new Properties();
            dataBaseProperties.load(new FileInputStream(PROPERTIES_FILE));
            connectionURL = dataBaseProperties.getProperty("db.url");
            user = dataBaseProperties.getProperty("db.user");
            password = dataBaseProperties.getProperty("db.password");
            driver = dataBaseProperties.getProperty("db.driver");

            Class.forName(driver);
        } catch (FileNotFoundException e) {
            System.err.println("File \"" + PROPERTIES_FILE + "\" not found\n");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println("Can't load \"" + PROPERTIES_FILE + "\"\n");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Driver " + driver + " not found\n");
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(connectionURL, user, password);
    }
}
