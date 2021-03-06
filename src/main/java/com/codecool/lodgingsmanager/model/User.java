package com.codecool.lodgingsmanager.model;

import com.codecool.lodgingsmanager.model.builder.AddressBuilder;
import com.codecool.lodgingsmanager.util.UserType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "site_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", unique = true)
    private long id;
    private String firstName;
    private String surname;
    private String email;
    private String phoneNumber;
    private String passwordHash;
    private UserType userType;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private AddressBuilder fullAddress;

    @ManyToMany(mappedBy = "userSet", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Lodgings> lodgingsSet = new HashSet<>();

    public User() {

    }

    public User(
            String firstName,
            String surname,
            String email,
            String phoneNumber,
            String passwordHash,
            UserType userType,
            AddressBuilder fullAddress
    ) {
        this.firstName = firstName;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.passwordHash = passwordHash;
        this.userType = userType;
        this.fullAddress = fullAddress;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCountry() {
        return fullAddress.getCountry();
    }

    public void setCountry(String country) {
        this.fullAddress.setCountry(country);
    }

    public String getCity() {
        return fullAddress.getCity();
    }

    public void setCity(String city) {
        this.fullAddress.setCity(city);
    }

    public String getZipCode() {
        return fullAddress.getZipCode();
    }

    public void setZipCode(String zipCode) {
        this.fullAddress.setZipCode(zipCode);
    }

    public String getAddress() {
        return fullAddress.getAddress();
    }

    public void setAddress(String address) {
        this.fullAddress.setAddress(address);
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getFullName() {
        return getFirstName() + " " + getSurname();
    }

    public AddressBuilder getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(AddressBuilder fullAddress) {
        this.fullAddress = fullAddress;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public Set<Lodgings> getLodgingsSet() {
        return lodgingsSet;
    }

    public void setLodgingsSet(Set<Lodgings> lodgingsSet) {
        this.lodgingsSet = lodgingsSet;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", country='" + getCountry() + '\'' +
                ", city='" + getCity() + '\'' +
                ", zipCode='" + getZipCode() + '\'' +
                ", address='" + getAddress() + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                '}';
    }
}
