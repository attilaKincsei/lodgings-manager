package com.codecool.lodgingsmanager.dao;

import com.codecool.lodgingsmanager.dao.implementation.database.EMDriver;
import com.codecool.lodgingsmanager.model.User;
import com.fasterxml.jackson.core.type.TypeReference;

import javax.persistence.*;
import java.util.List;

public abstract class BaseDAO<T> {

    protected EntityManager em = EMDriver.getEntityManager();


    public abstract List<T> getAll();
    public abstract T find(long id);

    public void add(T object) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(object);
        transaction.commit();
    }

    public void update(T object) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.merge(object);
        transaction.commit();
    }


    public void remove(long id) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(find(id));
        transaction.commit();
    }




}
