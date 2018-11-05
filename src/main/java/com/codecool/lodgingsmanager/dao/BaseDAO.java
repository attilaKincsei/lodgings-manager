package com.codecool.lodgingsmanager.dao;

import java.util.List;

public interface BaseDAO<T> {

    List<T> getAll();
    T find(long id);
    void add(T object);
    void update(T object);
    void remove(long id);
}
