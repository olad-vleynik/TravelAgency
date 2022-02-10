package com.gmail.vleynik.olad.travelagency.dao.entity;

import java.io.Serializable;
import java.util.Objects;

public class SavedEntry implements Serializable {

    private static final long serialVersionUID = 1L;

    private String uuId;
    private int userId;

    public SavedEntry(String uuId, int userId) {
        this.userId = userId;
        this.uuId = uuId;
    }

    public SavedEntry() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUuId() {
        return uuId;
    }

    public void setUuId(String uuId) {
        this.uuId = uuId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SavedEntry that = (SavedEntry) o;
        return userId == that.userId && Objects.equals(uuId, that.uuId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, uuId);
    }
}
