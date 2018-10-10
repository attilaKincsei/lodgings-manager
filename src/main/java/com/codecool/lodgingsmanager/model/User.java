package com.codecool.lodgingsmanager.model;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class User {

    String firstName;
    String surname;
    String email;
    String phoneNumber;
    String country;
    String city;
    String zipCode;
    String address;
    String password;

    public User(String firstName, String surname, String email, String phoneNumber, String country, String city, String zipCode, String address) {
        this.firstName = firstName;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.country = country;
        this.city = city;
        this.zipCode = zipCode;
        this.address = address;
    }

    protected User() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
