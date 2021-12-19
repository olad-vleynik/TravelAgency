package com.gmail.vleynik.olad.nonametourismagency.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {
    private static final ComboPooledDataSource dataSource = new ComboPooledDataSource();
    private static boolean initialized;

    public static Connection getConnection() throws SQLException {
        initialize();
        return dataSource.getConnection();
    }

    private static void initialize() {
        if (initialized) return;

        Properties props = new Properties();
        try {
            props.load(ConnectionFactory.class.getClassLoader().getResourceAsStream("database.properties"));

            dataSource.setDriverClass(props.getProperty("db.driver"));
            dataSource.setJdbcUrl(props.getProperty("db.url"));
            dataSource.setUser(props.getProperty("db.user"));
            dataSource.setPassword(props.getProperty("db.password"));

            initialized = true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
