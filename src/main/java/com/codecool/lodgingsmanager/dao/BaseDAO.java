package com.codecool.lodgingsmanager.dao;

import com.codecool.lodgingsmanager.dao.util.EMDriver;

import javax.persistence.*;
import java.util.List;

public interface BaseDAO<T> {

    EntityManager em = EMDriver.getEntityManager();


    List<T> getAll();
    T find(long id);

    void add(T object);
    void update(T object);
    void remove(long id);


//    public void add(T object) {
//        EntityTransaction transaction = em.getTransaction();
//        transaction.begin();
//        em.persist(object);
//        transaction.commit();
//    }
//
//    public void update(T object) {
//        EntityTransaction transaction = em.getTransaction();
//        transaction.begin();
//        em.merge(object);
//        transaction.commit();
//    }
//
//
//    public void remove(long id) {
//        EntityTransaction transaction = em.getTransaction();
//        transaction.begin();
//        em.remove(find(id));
//        transaction.commit();
//    }
//

}
