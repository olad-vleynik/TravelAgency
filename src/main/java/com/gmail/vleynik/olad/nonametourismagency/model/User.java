package com.gmail.vleynik.olad.nonametourismagency.model;

import java.util.Date;

public class User {
    private String firstName;
    private String secondName;
    private String phoneNumber;
    private String email;
    private String password;
    //private String language; //cookies?
    private Date birthDay;
    private double personalDiscount;
    private double maxDiscount;
    private AccessLevel accessLevel;
    private boolean isBanned;

    {
        isBanned = false;
    }

    private enum AccessLevel {
        ADMINISTRATOR,
        MANAGER,
        CLIENT
    }
}
