package com.codecool.lodgingsmanager.model;

public class Tenant extends User {

    public Tenant(String firstName, String surname, String email, String phoneNumber, String country, String city, String zipCode, String address) {
        super(firstName, surname, email, phoneNumber, country, city, zipCode, address);
    }
}
