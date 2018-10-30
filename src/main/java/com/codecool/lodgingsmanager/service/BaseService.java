package com.codecool.lodgingsmanager.service;

import com.codecool.lodgingsmanager.model.User;

import java.util.List;

public interface BaseService <E> {

    void injectDependency(BaseService handler);
    void handleAddNew(E object);
    User handleGetUserBy(String userEmail);
    List<E> handleGetAllBy(long id);
    void handleUpdate(E object);
    void handleDeletion(long id);
}
