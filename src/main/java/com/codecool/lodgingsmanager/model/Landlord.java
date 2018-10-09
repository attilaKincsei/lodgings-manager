package com.codecool.lodgingsmanager.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "landlord")
public class Landlord extends User implements Managing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "landlord")
    Set<Apartment> apartments = new HashSet<>();

    public Landlord(String firstName, String surname, String email, String phoneNumber, String country, String city, String zipCode, String address) {
        super(firstName, surname, email, phoneNumber, country, city, zipCode, address);
    }
}
