package com.codecool.lodgingsmanager.model;

import com.codecool.lodgingsmanager.util.UserType;

public abstract class UserManager extends User {

    private static final UserType USER_MANAGER = UserType.USER_MANAGER;

    public UserManager() {
    }

    public UserManager(
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
}
