package com.gmail.vleynik.olad.travelagency.dao.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Tour implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private String info;
    private String country;
    private TourState tourState;
    private TourType tourType;
    private boolean isHot;
    private LocalDate date;
    private int nightsCount;
    private int personsCount;
    private int hotelRating;
    private String hotelName;
    private TransferType transferType;
    private int cost;
    private String previewFile;

    public Tour(int id, String name, String info, String country, TourState tourState, TourType tourType,
                boolean isHot, LocalDate date, int nightsCount, int personsCount, int hotelRating, String hotelName,
                TransferType transferType, int cost, String previewFile) {
        this.id = id;
        this.name = name;
        this.info = info;
        this.country = country;
        this.tourState = tourState;
        this.tourType = tourType;
        this.isHot = isHot;
        this.date = date;
        this.nightsCount = nightsCount;
        this.personsCount = personsCount;
        this.hotelRating = hotelRating;
        this.hotelName = hotelName;
        this.transferType = transferType;
        this.cost = cost;
        this.previewFile = previewFile;
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

    public com.gmail.vleynik.olad.travelagency.dao.entity.Tour setInfo(String info) {
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

    public TourState getTourState() {
        return tourState;
    }

    public Tour setTourState(TourState tourState) {
        this.tourState = tourState;
        return this;
    }

    public TourType getTourType() {
        return tourType;
    }

    public Tour setTourType(TourType tourType) {
        this.tourType = tourType;
        return this;
    }

    public boolean isHot() {
        return isHot;
    }

    public com.gmail.vleynik.olad.travelagency.dao.entity.Tour setHot(boolean hot) {
        isHot = hot;
        return this;
    }

    public LocalDate getDate() {
        return date;
    }

    public com.gmail.vleynik.olad.travelagency.dao.entity.Tour setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public int getNightsCount() {
        return nightsCount;
    }

    public com.gmail.vleynik.olad.travelagency.dao.entity.Tour setNightsCount(int nightsCount) {
        this.nightsCount = nightsCount;
        return this;
    }

    public int getHotelRating() {
        return hotelRating;
    }

    public com.gmail.vleynik.olad.travelagency.dao.entity.Tour setHotelRating(int hotelRating) {
        this.hotelRating = hotelRating;
        return this;
    }

    public String getHotelName() {
        return hotelName;
    }

    public com.gmail.vleynik.olad.travelagency.dao.entity.Tour setHotelName(String hotelName) {
        this.hotelName = hotelName;
        return this;
    }

    public TransferType getTransferType() {
        return transferType;
    }

    public com.gmail.vleynik.olad.travelagency.dao.entity.Tour setTransferType(TransferType transferType) {
        this.transferType = transferType;
        return this;
    }

    public int getCost() {
        return cost;
    }

    public com.gmail.vleynik.olad.travelagency.dao.entity.Tour setCost(int cost) {
        this.cost = cost;
        return this;
    }

    public String getPreviewFile() {
        return previewFile;
    }

    public com.gmail.vleynik.olad.travelagency.dao.entity.Tour setPreviewFile(String previewFile) {
        this.previewFile = previewFile;
        return this;
    }

    public int getPersonsCount() {
        return personsCount;
    }

    public void setPersonsCount(int personsCount) {
        this.personsCount = personsCount;
    }

    public enum TourType {
        RELAX("relax"),
        SIGHTSEEING("sightseeing"),
        SHOPPING("shopping");

        private String name;

        private TourType(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public enum TransferType {
        AIRPLANE("airplane"),
        BUS("bus"),
        TRAIN("train"),
        CRUISE_LINER("cruise.liner");

        private String name;

        private TransferType(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public enum TourState {
        AVAILABLE("available"),
        ONGOING("ongoing"),
        COMPLETED("completed"),
        CANCELED("canceled");

        private String name;

        private TourState(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        com.gmail.vleynik.olad.travelagency.dao.entity.Tour tour = (com.gmail.vleynik.olad.travelagency.dao.entity.Tour) o;
        return id == tour.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
