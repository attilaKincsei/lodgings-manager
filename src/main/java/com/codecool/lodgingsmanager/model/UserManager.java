package com.codecool.lodgingsmanager.model;

import com.codecool.lodgingsmanager.model.builder.AddressBuilder;
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
            String passwordHash,
            AddressBuilder fullAddress
    ) {
        super(firstName, surname, email, phoneNumber, passwordHash, fullAddress);
    }
}
