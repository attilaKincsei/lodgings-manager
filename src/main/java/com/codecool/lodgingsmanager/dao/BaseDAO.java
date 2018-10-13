package com.codecool.lodgingsmanager.dao;

import com.codecool.lodgingsmanager.dao.implementation.database.EMDriver;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public abstract class BaseDAO<T> {

    protected EntityManager em = EMDriver.getEntityManager();

    public void add(T object) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(object);
        transaction.commit();
    }

    public abstract T find(int id);


    public void update(T object) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.merge(object);
        transaction.commit();
    }


    public void remove(int id) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(find(id));
        transaction.commit();
    }




    public abstract List<T> getAll();

}
