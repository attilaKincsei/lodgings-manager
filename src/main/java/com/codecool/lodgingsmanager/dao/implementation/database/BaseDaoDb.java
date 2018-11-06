package com.codecool.lodgingsmanager.dao.implementation.database;

import com.codecool.lodgingsmanager.dao.BaseDAO;
import com.codecool.lodgingsmanager.dao.util.EMDriver;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public abstract class BaseDaoDb<T> implements BaseDAO<T> {

    protected Class<T> classType;

    protected BaseDaoDb(Class<T> classType) {
        this.classType = classType;
    }

    @Override
    public void add(T object) {
        EntityManager em = EMDriver.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(object);
        transaction.commit();
        em.close();
    }
    @Override
    public void update(T object) {
        EntityManager em = EMDriver.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.merge(object);
        transaction.commit();
        em.close();
    }
}
