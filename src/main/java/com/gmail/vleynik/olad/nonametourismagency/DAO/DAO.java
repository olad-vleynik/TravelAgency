package com.gmail.vleynik.olad.nonametourismagency.DAO;

public interface DAO<T> {
    void createTable();

    void addNew(T t);
    T get(int id);
    void update(T t, String[] params);
    void delete(T t);
}
