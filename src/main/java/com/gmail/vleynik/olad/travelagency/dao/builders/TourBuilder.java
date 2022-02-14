package com.gmail.vleynik.olad.travelagency.dao.builders;

import com.gmail.vleynik.olad.travelagency.dao.entity.Tour;
import com.gmail.vleynik.olad.travelagency.dao.entity.User;

import java.time.LocalDate;
import java.util.Date;

public class TourBuilder {

    private int id;
    private String name;
    private String info;
    private String country;
    private Tour.State state;
    private Tour.TourType type;
    private boolean isHot;
    private LocalDate date;
    private int nightsCount;
    private int hotelRating;
    private String hotelName;
    private Tour.TransferType transferType;
    private double costInUSD;
    private String previewPath;

    public TourBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public TourBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public TourBuilder setInfo(String info) {
        this.info = info;
        return this;
    }

    public TourBuilder setCountry(String country) {
        this.country = country;
        return this;
    }

    public TourBuilder setState(Tour.State state) {
        this.state = state;
        return this;
    }

    public TourBuilder setType(Tour.TourType type) {
        this.type = type;
        return this;
    }

    public TourBuilder setHot(boolean hot) {
        isHot = hot;
        return this;
    }

    public TourBuilder setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public TourBuilder setNightsCount(int nightsCount) {
        this.nightsCount = nightsCount;
        return this;
    }

    public TourBuilder setHotelRating(int hotelRating) {
        this.hotelRating = hotelRating;
        return this;
    }

    public TourBuilder setHotelName(String hotelName) {
        this.hotelName = hotelName;
        return this;
    }

    public TourBuilder setTransferType(Tour.TransferType transferType) {
        this.transferType = transferType;
        return this;
    }

    public TourBuilder setCostInUSD(double costInUSD) {
        this.costInUSD = costInUSD;
        return this;
    }

    public TourBuilder setPreviewPath(String previewPath) {
        this.previewPath = previewPath;
        return this;
    }

    public Tour build() {
        return new Tour(id, name, info, country, state, type, isHot, date, nightsCount, hotelRating, hotelName,
                transferType, costInUSD, previewPath);
    }
}
