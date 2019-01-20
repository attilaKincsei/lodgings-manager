package com.codecool.lodgingsmanager.service;

import com.codecool.lodgingsmanager.model.User;

import java.util.List;

public interface BaseService <E> {

    User handleGetUserBy(String userEmail);
    List<E> handleGetAllBy(long id);
    void handleDeletion(long id);

    List<String> getEnumAsStringList();

    String handleCrudGetBy(String requestPath, String id);

    void handleAddPost(E object);
}
