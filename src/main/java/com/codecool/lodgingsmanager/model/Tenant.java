package com.codecool.lodgingsmanager.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "TENANT")
public class Tenant extends User {

    public Tenant() {
    }

    public Tenant(
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
