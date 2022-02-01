package com.gmail.vleynik.olad.travelagency.dao.entity;

import java.util.Date;
import java.util.Objects;

public class User {
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
    private AccessLevel accessLevel;
    //private Language lang;

    public User() {
    }

    public User(int id, String name, String surname, String phoneNumber, String email,
                String password, Date birthDay, double balanceInUSD, double personalDiscount,
                double maxDiscount, boolean isBanned, AccessLevel accessLevel) {
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
        this.accessLevel = accessLevel;
    }

    public enum AccessLevel {
        ADMINISTRATOR,
        MANAGER,
        CLIENT
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

    public AccessLevel getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(AccessLevel accessLevel) {
        this.accessLevel = accessLevel;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", birthDay=" + birthDay +
                ", balanceInUSD=" + balanceInUSD +
                ", personalDiscount=" + personalDiscount +
                ", maxDiscount=" + maxDiscount +
                ", isBanned=" + isBanned +
                ", accessLevel=" + accessLevel +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Double.compare(user.balanceInUSD, balanceInUSD) == 0 && Double.compare(user.personalDiscount, personalDiscount) == 0 && Double.compare(user.maxDiscount, maxDiscount) == 0 && isBanned == user.isBanned && Objects.equals(name, user.name) && Objects.equals(surname, user.surname) && Objects.equals(phoneNumber, user.phoneNumber) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(birthDay, user.birthDay) && accessLevel == user.accessLevel;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, phoneNumber, email, password, birthDay, balanceInUSD, personalDiscount, maxDiscount, isBanned, accessLevel);
    }

    public static class Builder {
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
        private AccessLevel accessLevel = AccessLevel.CLIENT;

        public Builder() {
        }

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public Builder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setBirthDay(Date birthDay) {
            this.birthDay = birthDay;
            return this;
        }

        public Builder setBalanceInUSD(double balanceInUSD) {
            this.balanceInUSD = balanceInUSD;
            return this;
        }

        public Builder setPersonalDiscount(double personalDiscount) {
            this.personalDiscount = personalDiscount;
            return this;
        }

        public Builder setMaxDiscount(double maxDiscount) {
            this.maxDiscount = maxDiscount;
            return this;
        }

        public Builder setBanned(boolean isBanned) {
            this.isBanned = isBanned;
            return this;
        }

        public Builder setAccessLevel(AccessLevel accessLevel) {
            this.accessLevel = accessLevel;
            return this;
        }

        public User build() {
            return new User(id, name, surname, phoneNumber, email, password, birthDay, balanceInUSD, personalDiscount,
                    maxDiscount, isBanned, accessLevel);
        }
    }
}
