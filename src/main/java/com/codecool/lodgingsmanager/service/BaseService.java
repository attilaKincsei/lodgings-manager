package com.codecool.lodgingsmanager.service;

import com.codecool.lodgingsmanager.model.Lodgings;
import com.codecool.lodgingsmanager.model.User;

import java.util.List;

public abstract class BaseService <E> {

    public abstract void injectDependency(BaseService handler);

    public abstract void handleAddNew(E object);

    public abstract User handleGetUserBy(String userEmail);
    public abstract List<E> handleGetListBy(long id);
    public abstract List<E> handleGetListBy(String stringParam, long id);

    public abstract void handleUpdate(E object);

    public abstract void handleDeletion(long id);

    public abstract List<Lodgings> handleGetAllLodgingsBy(long userId);
}
