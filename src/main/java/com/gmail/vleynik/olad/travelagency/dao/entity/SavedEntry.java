package com.gmail.vleynik.olad.travelagency.dao.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class SavedEntry implements Serializable {

    private static final long serialVersionUID = 1L;

    private String uuId;
    private int userId;
    private LocalDate validUntil;

    public SavedEntry(String uuId, int userId, LocalDate validUntil) {
        this.userId = userId;
        this.uuId = uuId;
        this.validUntil = validUntil;
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

    public LocalDate getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(LocalDate validUntil) {
        this.validUntil = validUntil;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SavedEntry that = (SavedEntry) o;
        return userId == that.userId && Objects.equals(uuId, that.uuId) && Objects.equals(validUntil, that.validUntil);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuId, userId, validUntil);
    }
}
