package com.gmail.vleynik.olad.travelagency.dao;

import com.gmail.vleynik.olad.travelagency.dao.entity.SavedEntry;
import com.gmail.vleynik.olad.travelagency.services.ConnectionService;

import java.sql.*;
import java.time.LocalDate;
import java.util.UUID;

public class SavedEntryDAO{

    private static final String INSERT_QUERY = "INSERT INTO saved_entries VALUES (?,?,?)";
    private static final String SELECT_BY_USER_ID_QUERY = "SELECT * FROM saved_entries WHERE userID=?";
    private static final String SELECT_BY_UUID_QUERY = "SELECT * FROM saved_entries WHERE uuId=?";
    private static final String DELETE_SAVED_ENTRY_QUERY = "DELETE FROM saved_entries WHERE uuId=?";
    private static final String DELETE_EXPIRED_ENTRIES_QUERY = "DELETE FROM saved_entries WHERE `validUntil` < NOW()";
    private static final String UPDATE_EXPIRE_DATE = "UPDATE saved_entries SET validUntil=?  WHERE userID=?";

    public int addNew(SavedEntry savedEntry) {
        try (Connection connection = ConnectionService.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {

            preparedStatement.setString(1, savedEntry.getUuId());
            preparedStatement.setInt(2, savedEntry.getUserId());
            preparedStatement.setDate(3, Date.valueOf(savedEntry.getValidUntil()));

            preparedStatement.executeUpdate();
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public SavedEntry getById(int id) {
        SavedEntry savedEntry = new SavedEntry("-1", id, LocalDate.now());
        try (Connection connection = ConnectionService.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_USER_ID_QUERY)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (!rs.isBeforeFirst()) {
                return savedEntry;
            }
            rs.next();
            savedEntry.setUuId(rs.getString("uuId"));
            savedEntry.setValidUntil(rs.getDate("validUntil").toLocalDate());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return savedEntry;
    }

    public SavedEntry getByUUID(String uuId) {
        SavedEntry savedEntry = new SavedEntry(uuId, -1, LocalDate.now());
        try (Connection connection = ConnectionService.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_UUID_QUERY)) {
            preparedStatement.setString(1, uuId);
            ResultSet rs = preparedStatement.executeQuery();
            if (!rs.isBeforeFirst()) {
                return savedEntry;
            }
            rs.next();
            savedEntry.setUserId(rs.getInt("userId"));
            savedEntry.setValidUntil(rs.getDate("validUntil").toLocalDate());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return savedEntry;
    }

    public void update(SavedEntry savedEntry) {
        try (Connection connection = ConnectionService.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_EXPIRE_DATE)) {

            preparedStatement.setDate(1, Date.valueOf(savedEntry.getValidUntil()));
            preparedStatement.setString(2, savedEntry.getUuId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        throw new IllegalStateException("please use deleteByUUID(String uuid) method");
    }

    public void deleteByUUID(String uuid) {
        try (Connection connection = ConnectionService.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SAVED_ENTRY_QUERY)) {

            preparedStatement.setString(1, uuid);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteExpiredSessions() {
        try (Connection connection = ConnectionService.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_EXPIRED_ENTRIES_QUERY)) {

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
