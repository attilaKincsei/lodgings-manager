package com.codecool.lodgingsmanager.service;

import com.codecool.lodgingsmanager.model.User;

import java.util.List;

public interface BaseService <E> {

    void handleAddNew(E object);
    User handleGetUserBy(String userEmail);
    List<E> handleGetAllBy(long id);
    void handleUpdate(E object);
    void handleDeletion(long id);

    String handleCRUDBy(String requestPath, String id);

    List<String> getEnumAsStringList();

    void handleAddOrEditWithPostRequest(
            String lodgingName, String lodgingType, String country, String city, String zipCode, String address,
            String dailyPrice, String electricityBill, String gasBill, String telecommunicationBill, String cleaningCost,
            String userEmail, String requestPath, String lodgingsIdString
    );
}
