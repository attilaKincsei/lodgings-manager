package com.codecool.lodgingsmanager.dao.implementation.database;

import com.codecool.lodgingsmanager.dao.UserDao;
import com.codecool.lodgingsmanager.model.User;

import javax.persistence.*;
import java.util.List;

public class UserDaoDb implements UserDao {

    @Override
    public void add(User object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("lodgingsmanagerPU");
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(object);
        transaction.commit();

        em.close();
        emf.close();
    }

    @Override
    public User find(int id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("lodgingsmanagerPU");
        EntityManager em = emf.createEntityManager();

        User user = em.find(User.class, id);

        em.close();
        emf.close();

        return user;
    }

    @Override
    public User findIdBy(String email) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("lodgingsmanagerPU");
        EntityManager em = emf.createEntityManager();

        List<User> userList = em.createQuery(
                "SELECT u " +
                        "FROM User u " +
                        "WHERE u.email = '" + email + "'")
                .getResultList();

        em.close();
        emf.close();
        return userList.get(0);

    }


    @Override
    public void remove(int id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("lodgingsmanagerPU");
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(find(id));
        transaction.commit();


        em.close();
        emf.close();

    }

    @Override
    public List<User> getAll() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("lodgingsmanagerPU");
        EntityManager em = emf.createEntityManager();

        Table table = Entity.class.getAnnotation(Table.class);
        String tableName = table.name();
        List<User> userList = em.createQuery("SELECT * FROM " + tableName).getResultList();

        em.close();
        emf.close();
        return userList;
    }

}
