package com.codecool.lodgingsmanager.service;

import com.codecool.lodgingsmanager.model.User;

import java.util.List;

public interface BaseService <E> {

    void handleAddNew(E object);
    User handleGetUserBy(String userEmail);
    List<E> handleGetAllBy(long id);
    void handleDeletion(long id);

    String handleCrudGetBy(String requestPath, String id);

    List<String> getEnumAsStringList();

    boolean handleAddAndEditPost(
            String param1, String param2, String param3, String param4, String param5,
            String param6, String param7, String param8, String param9, String param10,
            String param11, String param12, String param13, String param14, String param15);

}
