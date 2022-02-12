package com.gmail.vleynik.olad.travelagency.dao;

import com.gmail.vleynik.olad.travelagency.dao.entity.SavedEntry;
import com.gmail.vleynik.olad.travelagency.utils.ConnectionUtil;

import java.sql.*;
import java.util.UUID;

public class SavedEntryDAO implements DAO<SavedEntry>{

    private static final String INSERT_QUERY = "INSERT INTO saved_entries VALUES (?,?)";
    private static final String SELECT_BY_USER_ID_QUERY = "SELECT * FROM saved_entries WHERE userID=?";
    private static final String SELECT_BY_UUID_QUERY = "SELECT * FROM saved_entries WHERE uuId=?";
    private static final String DELETE_SAVED_ENTRY_QUERY = "DELETE FROM saved_entries WHERE userId=?";

    @Override
    public int addNew(SavedEntry savedEntry) {
        if (!getById(savedEntry.getUserId()).getUuId().equals("-1")) {
            delete(savedEntry.getUserId());
        }
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {

            preparedStatement.setString(1, savedEntry.getUuId());
            preparedStatement.setInt(2, savedEntry.getUserId());

            preparedStatement.executeUpdate();
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public SavedEntry getById(int id) {
        SavedEntry savedEntry = new SavedEntry("-1", id);
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_USER_ID_QUERY)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (!rs.isBeforeFirst()) {
                return savedEntry;
            }
            rs.next();
            savedEntry.setUuId(rs.getString("uuId"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return savedEntry;
    }

    public SavedEntry getByUUID(String uuId) {
        SavedEntry savedEntry = new SavedEntry(uuId, -1);
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_UUID_QUERY)) {
            preparedStatement.setString(1, uuId);
            ResultSet rs = preparedStatement.executeQuery();
            if (!rs.isBeforeFirst()) {
                return savedEntry;
            }
            rs.next();
            savedEntry.setUserId(rs.getInt("userId"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return savedEntry;
    }

    @Override
    public void update(SavedEntry savedEntry, String[] params) {
        throw new IllegalStateException("no need to use");
    }

    @Override
    public void delete(int id) {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SAVED_ENTRY_QUERY)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean isSessionIdUnique(String uuId) {
        return getByUUID(uuId).getUserId() == -1;
    }

    public synchronized String getUniqueUUID() {
        String uuId;
        do {
            uuId = UUID.randomUUID().toString();
        }while (!isSessionIdUnique(uuId));
        return uuId;
    }
}
