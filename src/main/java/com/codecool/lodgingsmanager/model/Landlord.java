package com.codecool.lodgingsmanager.model;

public class Landlord extends User implements Managing {

    public Landlord(String firstName, String surname, String email, String phoneNumber, String country, String city, String zipCode, String address) {
        super(firstName, surname, email, phoneNumber, country, city, zipCode, address);
    }
}
