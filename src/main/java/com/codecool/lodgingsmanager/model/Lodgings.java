package com.codecool.lodgingsmanager.model;

import com.codecool.lodgingsmanager.util.LodgingsType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Lodgings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Enumerated
    private LodgingsType lodgingsType;
    private String country;
    private String city;
    private String zipCode;
    private String address;

    private long pricePerDay;
    private long electricityBill;
    private long gasBill;
    private long telecommunicationBill;
    private long cleaningCost;

    @OneToMany(mappedBy = "lodgings", fetch = FetchType.LAZY)
    private Set<ToDo> todos = new HashSet<ToDo>();

    @ManyToOne
    private User landlord;

    @ManyToOne
    private User propertyManager;

//    @OneToMany(mappedBy = "tenantLodgings", fetch = FetchType.LAZY)
//    private Set<User> tenants = new HashSet<>();
//

    public Lodgings(
            String name, LodgingsType lodgingsType, String country, String city, String zipCode, String address,
            long pricePerDay, long electricityBill, long gasBill, long telecommunicationBill, long cleaningCost,
            User landlord
    ) {
        this.name = name;
        this.lodgingsType = lodgingsType;
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


    public Lodgings(
            String name, LodgingsType lodgingsType, String country, String city, String zipCode, String address,
            long pricePerDay, long electricityBill, long gasBill, long telecommunicationBill, long cleaningCost,
            User landlord, User propertyManager
    ) {
        this.name = name;
        this.lodgingsType = lodgingsType;
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
        this.propertyManager = propertyManager;
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

    public LodgingsType getLodgingsType() {
        return lodgingsType;
    }

    public void setLodgingsType(LodgingsType lodgingsType) {
        this.lodgingsType = lodgingsType;
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

    public User getLandlord() {
        return landlord;
    }

    public void setLandlord(User landlord) {
        this.landlord = landlord;
    }

    public User getPropertyManager() {
        return propertyManager;
    }

    public void setPropertyManager(User propertyManager) {
        this.propertyManager = propertyManager;
    }

//    public Set<User> getTenants() {
//        return tenants;
//    }
//
//    public void addTenant(Tenant tenant) {
//        tenants.add(tenant);
//    }
//
//    public void removeTenant(Tenant tenant) {
//        tenants.remove(tenant);
//    }

    public void addTodo(ToDo toDo){
        this.todos.add(toDo);
    }

    public Set<ToDo> getTodos() {
        return todos;
    }

    public void setTodos(Set<ToDo> todos) {
        this.todos = todos;
    }

    @Override
    public String toString() {
        return "Lodgings{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lodgingsType=" + lodgingsType +
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


