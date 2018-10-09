package com.codecool.lodgingsmanager.model;

import javax.persistence.*;

@Entity
@Table(name = "apartment")
public class Apartment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String address;

    @ManyToOne
    private Landlord landlord;

    public Apartment() {
    }

    public Apartment(String address, Landlord landlord) {
        this.address = address;
        this.landlord = landlord;
    }
}
