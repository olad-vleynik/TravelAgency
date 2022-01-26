package com.gmail.vleynik.olad.nonametourismagency.utils;

import org.apache.commons.dbcp2.BasicDataSource;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
    private static final String PROPERTIES_FILE = "/database.properties";
    private static final int INIT_CONNECTIONS_COUNT = 5;
    private static final int MAX_CONNECTIONS_COUNT = 10;

    private static BasicDataSource dataSource;

    public synchronized static Connection getConnection() {
        Connection connection = null;

        try {
            if (dataSource == null) {
                Properties dataBaseProperties = new Properties();
                InputStream propertiesInputStream = ConnectionUtil.class.getResourceAsStream(PROPERTIES_FILE);
                dataBaseProperties.load(propertiesInputStream);

                dataSource = new BasicDataSource();
                dataSource.setDriverClassName(dataBaseProperties.getProperty("db.driver"));
                dataSource.setUrl(dataBaseProperties.getProperty("db.url"));
                dataSource.setUsername(dataBaseProperties.getProperty("db.user"));
                dataSource.setPassword(dataBaseProperties.getProperty("db.password"));

                dataSource.setInitialSize(INIT_CONNECTIONS_COUNT);
                dataSource.setMaxTotal(MAX_CONNECTIONS_COUNT);

                if (propertiesInputStream == null) throw new FileNotFoundException();
                propertiesInputStream.close();
            }

            connection = dataSource.getConnection();
        } catch (FileNotFoundException e) {
            System.err.println("File \"" + PROPERTIES_FILE + "\" not found\n");
            e.printStackTrace();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        return connection;
    }
}
