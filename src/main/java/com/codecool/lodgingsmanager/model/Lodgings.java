package com.codecool.lodgingsmanager.model;

import com.codecool.lodgingsmanager.model.enums.Type;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Lodgings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @Enumerated
    private Type type;

    //private Date paymentDeadline;
    private long pricePerDay;
    private long electricityBill;
    private long gasBill;
    private long heatingBill;
    private long telecommunicationBill;
    private long cleaningCost;
    private String country;
    private String city;
    private int zipCode;

    public Lodgings(String name, Type type, String country, String city, int zipCode, Date paymentDeadline, long pricePerDay, long electricityBill, long gasBill, long heatingBill, long telecommunicationBill, long cleaningCost) {
        this.name = name;
        this.type = type;
        this.country = country;
        this.city = city;
        this.zipCode = zipCode;
        //this.paymentDeadline = paymentDeadline;
        this.pricePerDay = pricePerDay;
        this.electricityBill = electricityBill;
        this.gasBill = gasBill;
        this.heatingBill = heatingBill;
        this.telecommunicationBill = telecommunicationBill;
        this.cleaningCost = cleaningCost;
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

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    /*public Date getPaymentDeadline() {
        return paymentDeadline;
    }

    public void setPaymentDeadline(Date paymentDeadline) {
        this.paymentDeadline = paymentDeadline;
    }*/

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

    public long getHeatingBill() {
        return heatingBill;
    }

    public void setHeatingBill(long heatingBill) {
        this.heatingBill = heatingBill;
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
}


