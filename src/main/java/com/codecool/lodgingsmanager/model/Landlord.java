package com.codecool.lodgingsmanager.model;


import javax.persistence.*;

@Entity
@DiscriminatorValue(value = "LANDLORD")
public class Landlord extends UserManager {


    public Landlord() {
    }

    public Landlord(
            String firstName,
            String surname,
            String email,
            String phoneNumber,
            String country,
            String city,
            String zipCode,
            String address,
            String passwordHash

    ) {
        super(firstName, surname, email, phoneNumber, country, city, zipCode, address, passwordHash);
    }

}
