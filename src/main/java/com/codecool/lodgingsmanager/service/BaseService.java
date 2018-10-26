package com.codecool.lodgingsmanager.service;

import com.codecool.lodgingsmanager.model.User;

import java.util.List;

public abstract class BaseService <E> {


    public abstract List<E> handleBy(String stringParam, long longParam);


    public abstract E handleBy(String param);

    public abstract void delete(long id);
}
