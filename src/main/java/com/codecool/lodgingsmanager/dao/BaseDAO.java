package com.codecool.lodgingsmanager.dao;

import java.util.List;

public interface BaseDAO<T> {

    void add(T object);
    void update(T object);
    void remove(long id);

    T find(long id);
    List<T> getAll();
}
