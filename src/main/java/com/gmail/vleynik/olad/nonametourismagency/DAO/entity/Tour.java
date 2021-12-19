package com.gmail.vleynik.olad.nonametourismagency.DAO.entity;

import java.util.Date;

public class Tour {
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

    private enum TourType {
        RELAX,
        SIGHTSEEING,
        SHOPPING
    }

    private enum State {
        AVAILABLE,
        BOOKED,
        PAYED,
        ONGOING,
        COMPLETED,
        CANCELED
    }
}
