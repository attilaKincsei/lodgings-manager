package com.codecool.lodgingsmanager.dao;

import com.codecool.lodgingsmanager.dao.implementation.database.EMDriver;

import javax.persistence.EntityManager;
import java.util.List;

public interface BaseDAO<T> {

    EntityManager em = EMDriver.getEntityManager();

    void add(T object);
    T find(int id);
    void remove(int id);
    List<T> getAll();

}
