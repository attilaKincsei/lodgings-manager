package com.codecool.lodgingsmanager.dao.implementation.database;

import com.codecool.lodgingsmanager.dao.UserDao;
import com.codecool.lodgingsmanager.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class UserDaoDb implements UserDao {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("lodgingsmanagerPU");
    private EntityManager em = emf.createEntityManager();

    @Override
    public void add(User object) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(object);
        transaction.commit();
    }

    @Override
    public User find(int id) {
        return em.find(User.class, id);
    }

    @Override
    public void remove(int id) {
        em.remove(find(id));

    }

    @Override
    public List<User> getAll() {
        return null;
    }
}
