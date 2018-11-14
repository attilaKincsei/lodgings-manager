package com.codecool.lodgingsmanager.model.builder;

import com.codecool.lodgingsmanager.model.User;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class AddressBuilder {

    @Id
    @GeneratedValue
    private Long id;
    private String country;
    private String city;
    private String zipCode;
    private String address;

    @OneToMany(mappedBy = "fullAddress", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<User> ownerList = new HashSet<>();

    public AddressBuilder() {
    }

    public AddressBuilder(String country, String city, String zipCode, String address) {
        this.country = country;
        this.city = city;
        this.zipCode = zipCode;
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<User> getOwnerList() {
        return ownerList;
    }

    public void setOwnerList(Set<User> ownerList) {
        this.ownerList = ownerList;
    }
}
