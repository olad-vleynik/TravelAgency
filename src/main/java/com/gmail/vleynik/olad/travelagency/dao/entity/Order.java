package com.gmail.vleynik.olad.travelagency.dao.entity;

import java.io.Serializable;

public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private int userId;
    private int tourId;

    private State state;

    public enum State {
        BOOKED,
        PAYED,
        CANCELED
    }
}
