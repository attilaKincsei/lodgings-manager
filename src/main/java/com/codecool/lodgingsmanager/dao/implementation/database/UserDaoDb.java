package com.codecool.lodgingsmanager.dao.implementation.database;

import com.codecool.lodgingsmanager.dao.UserDao;
import com.codecool.lodgingsmanager.model.Landlord;
import com.codecool.lodgingsmanager.model.User;
import com.sun.deploy.security.ValidationState;

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

        em.close();
        emf.close();
    }

    @Override
    public User find(int id) {

        return em.find(User.class, id);
    }

    @Override
    public User findIdBy(String email) {

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
    public List<String> getAllEmailAddresses() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("lodgingsmanagerPU");
        EntityManager em = emf.createEntityManager();

        List<String> emailList = em.createQuery(
                "SELECT u.email FROM User u")
                .getResultList();

        em.close();
        emf.close();
        return emailList;

    }


    @Override
    public void remove(int id) {

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(find(id));
        transaction.commit();

        em.close();
        emf.close();

    }

    @Override
    public List<User> getAll() {

        Table table = Entity.class.getAnnotation(Table.class);
        String tableName = table.name();
        List<User> userList = em.createQuery("SELECT * FROM " + tableName).getResultList();

        em.close();
        emf.close();
        return userList;
    }

}
