package com.gmail.vleynik.olad.travelagency.dao;

import com.gmail.vleynik.olad.travelagency.dao.entity.Tour;

import java.sql.SQLException;

public class TourDAO implements DAO<Tour>{
    @Override
    public int addNew(Tour tour) {
        return 0;
    }

    @Override
    public Tour getById(int id) throws SQLException {
        return null;
    }

    @Override
    public void update(Tour tour) {

    }

    @Override
    public void delete(int id) {

    }
}
