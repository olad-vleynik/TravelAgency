package com.gmail.vleynik.olad.travelagency.utils;

import org.apache.commons.dbcp2.BasicDataSource;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Utility class used to initialize connection pool and return connection
 *
 * @author Vladyslav Oliinyk.
 * @version 1.0
 */
public class ConnectionUtil {
    private static final String PROPERTIES_FILE = "/database.properties";
    private static final int INIT_CONNECTIONS_COUNT = 5;
    private static final int MAX_CONNECTIONS_COUNT = 10;

    private static BasicDataSource dataSource;

    /**
     * Private constructor excludes creation object of utility class
     *
     * @throws IllegalStateException on calling
     */
    private ConnectionUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Provides connection from connection pool
     *
     * @return connection from connection pool declared in {@link ConnectionUtil#dataSource}
     * @throws SQLException if {@link ConnectionUtil#dataSource} == null or DB is missing
     */
    public static Connection getConnection() throws SQLException {
        if (dataSource == null) {
            throw new SQLException("Data source not configured");
        }
        return dataSource.getConnection();
    }

    /**
     * Initialize connection pool {@link ConnectionUtil#dataSource}
     *
     * @throws FileNotFoundException if database properties file ({@link ConnectionUtil#PROPERTIES_FILE}) not found
     * @throws IOException if input stream can't read database properties file ({@link ConnectionUtil#PROPERTIES_FILE})
     */
    public static void init() throws IOException {
        try {
            if (dataSource == null) {
                Properties dataBaseProperties = new Properties();
                InputStream propertiesInputStream = ConnectionUtil.class.getResourceAsStream(PROPERTIES_FILE);

                if (propertiesInputStream == null)
                    throw new FileNotFoundException();

                dataBaseProperties.load(propertiesInputStream);

                dataSource = new BasicDataSource();
                dataSource.setDriverClassName(dataBaseProperties.getProperty("db.driver"));
                dataSource.setUrl(dataBaseProperties.getProperty("db.url"));
                dataSource.setUsername(dataBaseProperties.getProperty("db.user"));
                dataSource.setPassword(dataBaseProperties.getProperty("db.password"));

                dataSource.setInitialSize(INIT_CONNECTIONS_COUNT);
                dataSource.setMaxTotal(MAX_CONNECTIONS_COUNT);

                propertiesInputStream.close();
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File \"" + PROPERTIES_FILE + "\" not found\n");
        } catch (IOException e) {
            throw new IOException("Can't read \"" + PROPERTIES_FILE + "\" file\n");
        }
    }
}
