package com.gmail.vleynik.olad.nonametourismagency;

import java.util.Date;

public class Tour {
    private State state;
    private boolean isHot;
    private Date date;
    private TourType type;
    private double costInUSD;
    private int minPersons;
    private int maxPersons;
    private int hotelRating;
    private String info;

    {
        state = State.AVAILABLE;
    }

    private enum TourType {
        RELAX,
        SIGHTSEEING,
        SHOPPING
    }

    private enum State {
        AVAILABLE,
        BOOKED,
        PAYED,
        CANCELED
    }
}
