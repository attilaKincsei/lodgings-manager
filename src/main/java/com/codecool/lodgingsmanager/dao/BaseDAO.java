package com.codecool.lodgingsmanager.dao;

import com.codecool.lodgingsmanager.dao.implementation.database.EMDriver;

import javax.persistence.*;
import java.util.List;

public abstract class BaseDAO<T> {

    protected EntityManager em = EMDriver.getEntityManager();

    public void add(T object) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(object);
        transaction.commit();
    }

    public abstract T find(long id);


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




    public abstract List<T> getAll();

//    public List<T> getAll(Class<T> aClass) throws NoResultException { // todo: try to make this generic
//        List<T> userList = em.createQuery("SELECT t FROM " + aClass.getClass().getName() + " t").getResultList();
//        return userList;
//    }

}
