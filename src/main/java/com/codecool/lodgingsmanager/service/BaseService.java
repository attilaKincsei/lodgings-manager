package com.codecool.lodgingsmanager.service;

import com.codecool.lodgingsmanager.model.Lodgings;
import com.codecool.lodgingsmanager.model.User;

import java.util.List;

public abstract class BaseService <E> {


    public abstract List<E> handleBy(String stringParam, long longParam);


    public abstract E handleBy(String param);

    public abstract void delete(long id);

    public abstract void handleAddNewLodgings(Lodgings newLodgings);

    public abstract List<Lodgings> handleGettingLodgingsBy(User user);

    public abstract void handleAddNewUser(User newUser);

    public abstract void handleUpdate(E user);
}
