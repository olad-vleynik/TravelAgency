package com.gmail.vleynik.olad.nonametourismagency.DAO;

public interface DAO<T> {
    int addNew(T t);
    T get(int id);
    void update(T t, String[] params);
    void delete(int id);
}
