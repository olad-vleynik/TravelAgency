package com.gmail.vleynik.olad.travelagency.dao.entity;

import java.io.Serializable;
import java.util.Date;

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
}
