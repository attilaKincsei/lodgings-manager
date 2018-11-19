package com.codecool.lodgingsmanager.model;

import com.codecool.lodgingsmanager.model.builder.AddressBuilder;

import javax.persistence.*;


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
            String passwordHash,
            AddressBuilder fullAddress
    ) {
        super(firstName, surname, email, phoneNumber, passwordHash, fullAddress);
    }

}
