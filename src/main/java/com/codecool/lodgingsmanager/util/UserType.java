package com.codecool.lodgingsmanager.util;

public enum UserType {
    LANDLORD("LANDLORD"),
    PROPERTY_MANAGER("PROPERTY_MANAGER"),
    TENANT("TENANT");

    private final String stringValue;

    UserType(String stringValue) {
        this.stringValue = stringValue;
    }

    public String getStringValue() {
        return stringValue;
    }
}
