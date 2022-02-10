package com.gmail.vleynik.olad.travelagency.dao.entity;

import java.io.Serializable;
import java.util.Date;

public class Tour implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private String info;
    private State state;
    private boolean isHot;
    private Date date;
    private int nightsCount;
    private TourType type;
    private double costInUSD;
    private int hotelRating;

    public enum TourType {
        RELAX,
        SIGHTSEEING,
        SHOPPING
    }

    public enum State {
        AVAILABLE,
        ONGOING,
        COMPLETED,
        CANCELED
    }
}
