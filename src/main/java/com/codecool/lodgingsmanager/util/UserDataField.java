package com.codecool.lodgingsmanager.util;

public enum UserDataField {
    FIRST_NAME("first_name"),
    SURNAME("surname"),
    USER_TYPE("user_type"),
    EMAIL_ADDRESS("email"),
    PASSWORD("password"),
    PASSWORD_CONFIRMATION("password_confirmation"),
    PASSWORD_HASH("password_hash"),
    PHONE_NUMBER("phone_number"),
    COUNTRY("country"),
    CITY("city"),
    ZIP_CODE("zip_code"),
    ADDRESS("address");

    private final String inputString;

    UserDataField(String inputString) {
        this.inputString = inputString;
    }

    public String getInputString() {
        return inputString;
    }
}
