package com.gmail.vleynik.olad.travelagency.dao.entity;

import java.io.Serializable;
import java.util.Date;

public class Tour implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private State state;
    private boolean isHot;
    private Date date;
    private int daysCount;
    private TourType type;
    private double costInUSD;
    private int minPersons;
    private int maxPersons;
    private int hotelRating;
    private String info;

    public enum TourType {
        RELAX,
        SIGHTSEEING,
        SHOPPING
    }

    public enum State {
        AVAILABLE,
        BOOKED,
        PAYED,
        ONGOING,
        COMPLETED,
        CANCELED
    }
}
