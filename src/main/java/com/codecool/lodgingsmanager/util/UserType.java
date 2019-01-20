package com.codecool.lodgingsmanager.util;

public enum UserType {
    LANDLORD("userSet"),
    PROPERTY_MANAGER("propertyManager"),
    TENANT("tenant"),
    GUEST("guest");

    private final String stringValue;

    UserType(String stringValue) {
        this.stringValue = stringValue;
    }

    public String getStringValue() {
        return stringValue;
    }
}
