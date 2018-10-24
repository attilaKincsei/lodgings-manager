package com.codecool.lodgingsmanager.util;

public enum DeletableType {
    COMMENT("comment"),
    USER("user"),
    LODGINGS("lodgings"),
    TODO("todo");

    private final String modelTypeString;

    DeletableType(String modelTypeString) {
        this.modelTypeString = modelTypeString;
    }

    public String getModelTypeString() {
        return modelTypeString;
    }

}
