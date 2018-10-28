
package com.codecool.lodgingsmanager.dao.implementation.database;

import com.codecool.lodgingsmanager.dao.UserDao;
import com.codecool.lodgingsmanager.model.User;

import javax.persistence.*;
import java.util.List;

public class UserDaoDb extends UserDao {

    @Override
    public User find(long id) throws NoResultException {
        return em.find(User.class, id);
    }

    @Override
    public List<User> getAll() throws NoResultException {
        TypedQuery<User> query = em.createQuery("SELECT t FROM User t", User.class);
        return query.getResultList();
    }

    @Override
    public User findIdBy(String email) throws NoResultException {
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.email = ?1", User.class);
        return query.setParameter(1, email).getSingleResult();

    }

    @Override
    public List<String> getAllEmailAddresses() throws NoResultException {
        TypedQuery<String> query = em.createQuery("SELECT u.email FROM User u", String.class);
        return query.getResultList();

    }



}