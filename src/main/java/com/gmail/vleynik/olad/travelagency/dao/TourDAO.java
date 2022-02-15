package com.gmail.vleynik.olad.travelagency.dao;

import com.gmail.vleynik.olad.travelagency.dao.entity.Tour;
import com.gmail.vleynik.olad.travelagency.utils.ConnectionUtil;

import java.sql.*;

public class TourDAO implements DAO<Tour> {
    private static final String INSERT_TOUR_QUERY =
            "INSERT INTO tours VALUES (DEFAULT, ?, ?, ?, DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    @Override
    public int addNew(Tour tour) {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TOUR_QUERY, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, tour.getName());
            preparedStatement.setString(2, tour.getInfo());
            preparedStatement.setString(3, tour.getCountry());
            preparedStatement.setString(4, tour.getType().name());
            preparedStatement.setBoolean(5, tour.isHot());
            preparedStatement.setDate(6, Date.valueOf(tour.getDate()));
            preparedStatement.setInt(7, tour.getNightsCount());
            preparedStatement.setInt(8, tour.getHotelRating());
            preparedStatement.setString(9, tour.getHotelName());
            preparedStatement.setString(10, tour.getTransferType().name());
            preparedStatement.setInt(11, tour.getCostInUSD());
            preparedStatement.setString(12, tour.getPreviewFile());

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
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
