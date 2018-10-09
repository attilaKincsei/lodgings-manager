package com.codecool.lodgingsmanager.dao.implementation.database;

import com.codecool.lodgingsmanager.dao.UserDao;
import com.codecool.lodgingsmanager.model.User;

import javax.persistence.*;
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
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(find(id));
        transaction.commit();

    }

    @Override
    public List<User> getAll() {
        Table table = Entity.class.getAnnotation(Table.class);
        String tableName = table.name();

        return em.createQuery("SELECT * FROM " + tableName).getResultList();
    }
}
