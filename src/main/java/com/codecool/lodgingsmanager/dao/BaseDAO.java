package com.codecool.lodgingsmanager.dao;

import java.util.List;

public interface BaseDAO<T> {

    void add(T object);
    T find(int id);
    void remove(int id);
    List<T> getAll();

}
