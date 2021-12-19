package com.gmail.vleynik.olad.nonametourismagency.DAO;

import com.gmail.vleynik.olad.nonametourismagency.DAO.entity.Client;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ClientDAO implements DAO<Client> {

    private final Connection connection;

    private final static String DROP_IF_EXISTS = "DROP TABLE IF EXISTS Clients";
    private final static String CREATE_TABLE = "CREATE TABLE Clients (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, name VARCHAR(128) NOT NULL, "
                                             + "surname VARCHAR(128) NOT NULL, phoneNumber VARCHAR(12) NOT NULL, email VARCHAR(128) NOT NULL, "
                                             + "password VARCHAR(128) NOT NULL, birthDay DATE DEFAULT NULL, balanceInUSD DOUBLE DEFAULT NULL, "
                                             + "personalDiscount DOUBLE DEFAULT NULL, maxDiscount DOUBLE DEFAULT NULL, isBanned BOOL DEFAULT FALSE)";

    public ClientDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void createTable() {
        try {
            try (Statement st = connection.createStatement()) {
                st.execute(DROP_IF_EXISTS);
                st.execute(CREATE_TABLE);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void addNew(Client client) {

    }

    @Override
    public Client get(int id) {
        return null;
    }

    @Override
    public void update(Client client, String[] params) {

    }

    @Override
    public void delete(Client client) {

    }
}
