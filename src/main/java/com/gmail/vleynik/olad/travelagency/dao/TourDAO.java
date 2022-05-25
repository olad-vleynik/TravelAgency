package com.gmail.vleynik.olad.travelagency.dao;

import com.gmail.vleynik.olad.travelagency.dao.builders.TourBuilder;
import com.gmail.vleynik.olad.travelagency.dao.entity.Tour;
import com.gmail.vleynik.olad.travelagency.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TourDAO {
    private static final String INSERT_TOUR_QUERY =
            "INSERT INTO tours VALUES (DEFAULT, ?, ?, ?, DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public static final String SELECT_TOUR_BY_ID = "SELECT tours.*, tour_states.name AS tourState, tour_types.name "
            + "AS tourType, transfer_types.name AS transferType FROM tours LEFT JOIN tour_states "
            + "ON tourStateId=tour_states.id LEFT JOIN tour_types ON tourTypeId=tour_types.id "
            + "LEFT JOIN transfer_types ON transferTypeId=transfer_types.id WHERE tours.id=?";

    public static final String SELECT_HOTTEST_TOURS = "SELECT tours.*, tour_states.name AS tourState, tour_types.name "
            + "AS tourType, transfer_types.name AS transferType FROM tours LEFT JOIN tour_states "
            + "ON tourStateId=tour_states.id LEFT JOIN tour_types ON tourTypeId=tour_types.id "
            + "LEFT JOIN transfer_types ON transferTypeId=transfer_types.id WHERE isHot=1 ORDER BY date ASC LIMIT 10";

    public static final String SELECT_NEAREST_NON_HOT_TOURS = "SELECT tours.*, tour_states.name AS tourState, tour_types.name "
            + "AS tourType, transfer_types.name AS transferType FROM tours LEFT JOIN tour_states "
            + "ON tourStateId=tour_states.id LEFT JOIN tour_types ON tourTypeId=tour_types.id "
            + "LEFT JOIN transfer_types ON transferTypeId=transfer_types.id WHERE isHot=0 ORDER BY date ASC LIMIT ?";

    private static final String UPDATE_TOUR_QUERY = "UPDATE tours SET name=? WHERE id=?";

    public int addNew(Tour tour) {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TOUR_QUERY, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, tour.getName());
            preparedStatement.setString(2, tour.getInfo());
            preparedStatement.setString(3, tour.getCountry());
            preparedStatement.setInt(4, tour.getTourType().ordinal() + 1);
            preparedStatement.setBoolean(5, tour.isHot());
            preparedStatement.setDate(6, Date.valueOf(tour.getDate()));
            preparedStatement.setInt(7, tour.getNightsCount());
            preparedStatement.setInt(8, tour.getPersonsCount());
            preparedStatement.setInt(9, tour.getHotelRating());
            preparedStatement.setString(10, tour.getHotelName());
            preparedStatement.setInt(11, tour.getTransferType().ordinal() + 1);
            preparedStatement.setInt(12, tour.getCost());
            preparedStatement.setString(13, tour.getPreviewFile());

            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();

            return (int) generatedKeys.getLong(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public List<Tour> getTenHottest() {
        List<Tour> tours = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_HOTTEST_TOURS)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                tours.add(mapResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tours;
    }

    public List<Tour> getNearestNonHot(int quantity) {
        List<Tour> tours = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_NEAREST_NON_HOT_TOURS)) {
            preparedStatement.setInt(1, quantity);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                tours.add(mapResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tours;
    }

    public Tour getById(int id) throws SQLException {
        Tour tour = new Tour();
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TOUR_BY_ID)) {

            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();
            if (!rs.isBeforeFirst()) {
                tour.setId(-1);
                return tour;
            }

            rs.next();

            tour = mapResultSet(rs);
        }
        return tour;
    }

    public void update(Tour tour) {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TOUR_QUERY)) {

            preparedStatement.setString(1, tour.getName());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {

    }

    private static Tour mapResultSet(ResultSet rs) throws SQLException {
        return new TourBuilder()
                .setId(rs.getInt("id"))
                .setName(rs.getString("name"))
                .setType(Tour.TourType.valueOf(rs.getString("tourType").toUpperCase(Locale.ROOT)))
                .setState(Tour.TourState.valueOf(rs.getString("tourState").toUpperCase(Locale.ROOT)))
                .setInfo(rs.getString("info"))
                .setCountry(rs.getString("country"))
                .setDate(rs.getDate("date").toLocalDate())
                .setTransferType(Tour.TransferType.valueOf(rs.getString("transferType").toUpperCase(Locale.ROOT).replace(' ', '_')))
                .setHotelName(rs.getString("hotelName"))
                .setHot(rs.getBoolean("isHot"))
                .setHotelRating(rs.getInt("hotelRating"))
                .setNightsCount(rs.getInt("nightsCount"))
                .setPersonsCount(rs.getInt("personsCount"))
                .setCostInUSD(rs.getInt("costInUSD"))
                .setPreviewFile(rs.getString("previewFile"))
                .build();
    }
}
