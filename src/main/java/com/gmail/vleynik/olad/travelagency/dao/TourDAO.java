package com.gmail.vleynik.olad.travelagency.dao;

import com.gmail.vleynik.olad.travelagency.dao.builders.TourBuilder;
import com.gmail.vleynik.olad.travelagency.dao.builders.UserBuilder;
import com.gmail.vleynik.olad.travelagency.dao.entity.Tour;
import com.gmail.vleynik.olad.travelagency.dao.entity.User;
import com.gmail.vleynik.olad.travelagency.utils.ConnectionUtil;

import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Locale;

public class TourDAO implements DAO<Tour> {
    private static final String INSERT_TOUR_QUERY =
            "INSERT INTO tours VALUES (DEFAULT, ?, ?, ?, DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_BY_ID_TOUR_QUERY = "SELECT * FROM tours WHERE id=?";

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
        Tour tour = new Tour();
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_TOUR_QUERY)) {


            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();
            if (!rs.isBeforeFirst()) {
                tour.setId(-1);
                return tour;
            }

            rs.next();

            tour = new TourBuilder()
                    .setId(rs.getInt("id"))
                    .setName(rs.getString("name"))
                    .setType(Tour.TourType.valueOf(rs.getString("type")))
                    .setState(Tour.State.valueOf(rs.getString("state")))
                    .setInfo(rs.getString("info"))
                    .setCountry(rs.getString("country"))
                    .setDate(rs.getDate("date").toLocalDate())
                    .setTransferType(Tour.TransferType.valueOf(rs.getString("transferType")))
                    .setHotelName(rs.getString("hotelName"))
                    .setHot(rs.getBoolean("isHot"))
                    .setHotelRating(rs.getInt("hotelRating"))
                    .setNightsCount(rs.getInt("nightsCount"))
                    .setCostInUSD(rs.getInt("costInUSD"))
                    .setPreviewFile(rs.getString("previewFile"))
                    .build();
        }
        return tour;
    }

    @Override
    public void update(Tour tour) {

    }

    @Override
    public void delete(int id) {

    }
}
