package com.codecool.lodgingsmanager.model;

import com.codecool.lodgingsmanager.util.UserType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue(value = "LANDLORD")
public class Landlord extends User implements Managing {

    @OneToMany(mappedBy = "landlord", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    Set<Lodgings> lodgings = new HashSet<>();

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

    @Override
    public String getUserType() {
        return "LANDLORD";
    }

    public Set<Lodgings> getLodgings() {
        return lodgings;
    }
}
