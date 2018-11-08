package com.codecool.lodgingsmanager.model;

import com.codecool.lodgingsmanager.util.UserType;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

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

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
