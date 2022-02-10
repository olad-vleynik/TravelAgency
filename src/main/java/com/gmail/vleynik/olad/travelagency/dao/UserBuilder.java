package com.gmail.vleynik.olad.travelagency.dao;

import com.gmail.vleynik.olad.travelagency.dao.entity.User;

import java.util.Date;

public class UserBuilder {
    private int id;
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private String password;
    private Date birthDay;

    private double balanceInUSD = 0;
    private double personalDiscount = 0;
    private double maxDiscount = 20;

    private boolean isBanned = false;
    private User.AccessLevel accessLevel = User.AccessLevel.CLIENT;

    public UserBuilder(int id, String name, String surname, String phoneNumber, String email, String password, Date birthDay) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.birthDay = birthDay;
    }

    public UserBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public UserBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public UserBuilder setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public UserBuilder setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public UserBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
        return this;
    }

    public UserBuilder setBalanceInUSD(double balanceInUSD) {
        this.balanceInUSD = balanceInUSD;
        return this;
    }

    public UserBuilder setPersonalDiscount(double personalDiscount) {
        this.personalDiscount = personalDiscount;
        return this;
    }

    public UserBuilder setMaxDiscount(double maxDiscount) {
        this.maxDiscount = maxDiscount;
        return this;
    }

    public UserBuilder setBanned(boolean isBanned) {
        this.isBanned = isBanned;
        return this;
    }

    public UserBuilder setAccessLevel(User.AccessLevel accessLevel) {
        this.accessLevel = accessLevel;
        return this;
    }

    public User build() {
        return new User(id, name, surname, phoneNumber, email, password, birthDay, balanceInUSD, personalDiscount,
                maxDiscount, isBanned, accessLevel);
    }
}
