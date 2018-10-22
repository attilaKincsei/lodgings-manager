package com.codecool.lodgingsmanager.model;

import com.codecool.lodgingsmanager.util.Type;

import javax.persistence.*;

@Entity
public class Lodgings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Enumerated
    private Type type;
    private String country;
    private String city;
    private String zipCode;
    private String address;

    private long pricePerDay;
    private long electricityBill;
    private long gasBill;
    private long telecommunicationBill;
    private long cleaningCost;
    @ManyToOne
    private Landlord landlord;

    public Lodgings(String name, Type type, String country, String city, String zipCode, String address, long pricePerDay, long electricityBill, long gasBill, long telecommunicationBill, long cleaningCost, Landlord landlord) {
        this.name = name;
        this.type = type;
        this.country = country;
        this.city = city;
        this.zipCode = zipCode;
        this.address = address;
        this.pricePerDay = pricePerDay;
        this.electricityBill = electricityBill;
        this.gasBill = gasBill;
        this.telecommunicationBill = telecommunicationBill;
        this.cleaningCost = cleaningCost;
        this.landlord = landlord;
    }

    public Lodgings() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
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

    public String  getAddress() {return address;}

    public void setAddress(String address) {
        this.address = address;}

    public long getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(long pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public long getElectricityBill() {
        return electricityBill;
    }

    public void setElectricityBill(long electricityBill) {
        this.electricityBill = electricityBill;
    }

    public long getGasBill() {
        return gasBill;
    }

    public void setGasBill(long gasBill) {
        this.gasBill = gasBill;
    }

    public long getTelecommunicationBill() {
        return telecommunicationBill;
    }

    public void setTelecommunicationBill(long telecommunicationBill) {
        this.telecommunicationBill = telecommunicationBill;
    }

    public long getCleaningCost() {
        return cleaningCost;
    }

    public void setCleaningCost(long cleaningCost) {
        this.cleaningCost = cleaningCost;
    }

    public Landlord getLandlord() {
        return landlord;
    }

    public void setLandlord(Landlord landlord) {
        this.landlord = landlord;
    }

    @Override
    public String toString() {
        return "Lodgings{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", address='" + address + '\'' +
                ", pricePerDay=" + pricePerDay +
                ", electricityBill=" + electricityBill +
                ", gasBill=" + gasBill +
                ", telecommunicationBill=" + telecommunicationBill +
                ", cleaningCost=" + cleaningCost +
                ", landlord=" + landlord.getFullName() +
                '}';
    }
}


