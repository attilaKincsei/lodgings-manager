package com.codecool.lodgingsmanager.util;

import com.codecool.lodgingsmanager.model.Landlord;
import com.codecool.lodgingsmanager.model.PropertyManager;
import com.codecool.lodgingsmanager.model.Tenant;
import com.codecool.lodgingsmanager.model.User;

public class UserFactory {

    public static User createUserInstanceBy(
            UserType type,
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
        User requiredUser;

        if (type == UserType.LANDLORD) {
            requiredUser = new Landlord(firstName, surname, email, phoneNumber, country, city, zipCode, address, passwordHash);
        } else if (type == UserType.PROPERTY_MANAGER) {
            requiredUser = new PropertyManager(firstName, surname, email, phoneNumber, country, city, zipCode, address, passwordHash);
        } else if (type == UserType.TENANT || type == UserType.GUEST) {
            requiredUser = new Tenant(firstName, surname, email, phoneNumber, country, city, zipCode, address, passwordHash);
        } else {
            //Todo: create logger here instead of sout
            System.out.println("There is no such user type as " + type.toString());
            requiredUser = null;
        }

        return requiredUser;
    }

}
