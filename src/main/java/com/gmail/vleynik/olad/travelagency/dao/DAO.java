package com.gmail.vleynik.olad.travelagency.dao;

import java.sql.SQLException;

/**
 * Interface with CRUD methods
 *
 * @author Vladyslav Oliinyk.
 * @version 1.0
 */
public interface DAO<T> {
    int addNew(T t);
    T getById(int id) throws SQLException;
    void update(T t, String[] params);
    void delete(int id);
}
