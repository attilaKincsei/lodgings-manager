package com.codecool.lodgingsmanager.model;

import com.codecool.lodgingsmanager.util.UserType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@DiscriminatorValue(value = "PROPERTY_MANAGER")
public class PropertyManager extends UserManager {


    public PropertyManager() {

    }

    public PropertyManager(
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
