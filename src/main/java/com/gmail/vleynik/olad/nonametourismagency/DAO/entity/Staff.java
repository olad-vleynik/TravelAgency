package com.gmail.vleynik.olad.nonametourismagency.DAO.entity;

import java.util.Date;

public class Staff {
    private int id;
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private String password;
    private Date birthDay;

    private double balanceInUSD;
    private double personalDiscount;

    private AccessLevel accessLevel;
    private boolean isBanned;
    //private String language; //cookies?

    {
        isBanned = false;
    }

    private enum AccessLevel {
        ADMINISTRATOR,
        MANAGER
    }
}
