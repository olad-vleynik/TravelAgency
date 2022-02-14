package com.gmail.vleynik.olad.travelagency.dao.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Tour implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private String info;
    private String country;
    private State state;
    private TourType type;
    private boolean isHot;
    private Date date;
    private int nightsCount;
    private int hotelRating;
    private String hotelName;
    private TransferType transferType;
    private double costInUSD;
    private String previewPath;

    public Tour(int id, String name, String info, String country, State state, TourType type,
                boolean isHot, Date date, int nightsCount, int hotelRating, String hotelName,
                TransferType transferType, double costInUSD, String previewPath) {
        this.id = id;
        this.name = name;
        this.info = info;
        this.country = country;
        this.state = state;
        this.type = type;
        this.isHot = isHot;
        this.date = date;
        this.nightsCount = nightsCount;
        this.hotelRating = hotelRating;
        this.hotelName = hotelName;
        this.transferType = transferType;
        this.costInUSD = costInUSD;
        this.previewPath = previewPath;
    }

    public Tour() {
    }

    public int getId() {
        return id;
    }

    public Tour setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Tour setName(String name) {
        this.name = name;
        return this;
    }

    public String getInfo() {
        return info;
    }

    public Tour setInfo(String info) {
        this.info = info;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public Tour setCountry(String country) {
        this.country = country;
        return this;
    }

    public State getState() {
        return state;
    }

    public Tour setState(State state) {
        this.state = state;
        return this;
    }

    public TourType getType() {
        return type;
    }

    public Tour setType(TourType type) {
        this.type = type;
        return this;
    }

    public boolean isHot() {
        return isHot;
    }

    public Tour setHot(boolean hot) {
        isHot = hot;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public Tour setDate(Date date) {
        this.date = date;
        return this;
    }

    public int getNightsCount() {
        return nightsCount;
    }

    public Tour setNightsCount(int nightsCount) {
        this.nightsCount = nightsCount;
        return this;
    }

    public int getHotelRating() {
        return hotelRating;
    }

    public Tour setHotelRating(int hotelRating) {
        this.hotelRating = hotelRating;
        return this;
    }

    public String getHotelName() {
        return hotelName;
    }

    public Tour setHotelName(String hotelName) {
        this.hotelName = hotelName;
        return this;
    }

    public TransferType getTransferType() {
        return transferType;
    }

    public Tour setTransferType(TransferType transferType) {
        this.transferType = transferType;
        return this;
    }

    public double getCostInUSD() {
        return costInUSD;
    }

    public Tour setCostInUSD(double costInUSD) {
        this.costInUSD = costInUSD;
        return this;
    }

    public String getPreviewPath() {
        return previewPath;
    }

    public Tour setPreviewPath(String previewPath) {
        this.previewPath = previewPath;
        return this;
    }

    public enum TourType {
        RELAX,
        SIGHTSEEING,
        SHOPPING
    }

    public enum TransferType {
        AIRPLANE,
        BUS,
        TRAIN,
        CRUISE_LINER
    }

    public enum State {
        AVAILABLE,
        ONGOING,
        COMPLETED,
        CANCELED
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tour tour = (Tour) o;
        return id == tour.id && isHot == tour.isHot && nightsCount == tour.nightsCount && hotelRating == tour.hotelRating
                && Double.compare(tour.costInUSD, costInUSD) == 0 && Objects.equals(name, tour.name)
                && Objects.equals(info, tour.info) && Objects.equals(country, tour.country) && state == tour.state
                && type == tour.type && Objects.equals(date, tour.date) && Objects.equals(hotelName, tour.hotelName)
                && transferType == tour.transferType && Objects.equals(previewPath, tour.previewPath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, info, country, state, type, isHot, date, nightsCount, hotelRating, hotelName,
                transferType, costInUSD, previewPath);
    }
}
