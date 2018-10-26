
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
        return em.createQuery("SELECT t FROM User t").getResultList();
    }

    @Override
    public User findIdBy(String email) throws NoResultException {
        return (User) em.createQuery(
                "SELECT u " +
                        "FROM User u " +
                        "WHERE u.email = '" + email + "'")
                .getSingleResult();

    }

    @Override
    public List<String> getAllEmailAddresses() throws NoResultException {
        return em.createQuery(
                "SELECT u.email FROM User u")
                .getResultList();

    }



}