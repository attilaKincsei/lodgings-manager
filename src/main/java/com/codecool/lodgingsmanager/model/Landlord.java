package com.codecool.lodgingsmanager.model;


import com.codecool.lodgingsmanager.model.builder.AddressBuilder;

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
            String passwordHash,
            AddressBuilder fullAddress
    ) {
        super(firstName, surname, email, phoneNumber, passwordHash, fullAddress);
    }

}
