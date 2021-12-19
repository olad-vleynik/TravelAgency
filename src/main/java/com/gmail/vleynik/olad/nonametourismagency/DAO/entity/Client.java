package com.gmail.vleynik.olad.nonametourismagency.DAO.entity;

import java.util.Date;

public class Client {
    private int id;
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private String password;
    private Date birthDay;

    private double balanceInUSD;
    private double personalDiscount;
    private double maxDiscount;

    private boolean isBanned;
    //private String language; //cookies?

    public Client(int id, String name, String surname, String phoneNumber, String email, String password, Date birthDay,
                  double balanceInUSD, double personalDiscount, double maxDiscount, boolean isBanned) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.birthDay = birthDay;
        this.balanceInUSD = balanceInUSD;
        this.personalDiscount = personalDiscount;
        this.maxDiscount = maxDiscount;
        this.isBanned = isBanned;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public double getBalanceInUSD() {
        return balanceInUSD;
    }

    public void setBalanceInUSD(double balanceInUSD) {
        this.balanceInUSD = balanceInUSD;
    }

    public double getPersonalDiscount() {
        return personalDiscount;
    }

    public void setPersonalDiscount(double personalDiscount) {
        this.personalDiscount = personalDiscount;
    }

    public double getMaxDiscount() {
        return maxDiscount;
    }

    public void setMaxDiscount(double maxDiscount) {
        this.maxDiscount = maxDiscount;
    }

    public boolean isBanned() {
        return isBanned;
    }

    public void setBanned(boolean banned) {
        isBanned = banned;
    }
}
