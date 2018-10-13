
package com.codecool.lodgingsmanager.dao.implementation.database;

import com.codecool.lodgingsmanager.dao.UserDao;
import com.codecool.lodgingsmanager.model.User;

import javax.persistence.*;
import java.util.List;

public class UserDaoDb extends UserDao {

    @Override
    public User find(int id) {
        return em.find(User.class, id);
    }

    @Override
    public List<User> getAll() { // todo: not working
//        Table table = Entity.class.getAnnotation(Table.class);
//        String tableName = table.name();
//        List<User> userList = em.createQuery("SELECT * FROM " + tableName).getResultList();
        return null;
    }

    @Override
    public User findIdBy(String email) {
        return (User) em.createQuery(
                "SELECT u " +
                        "FROM User u " +
                        "WHERE u.email = '" + email + "'")
                .getSingleResult();

    }

    @Override
    public List<String> getAllEmailAddresses() {
        return em.createQuery(
                "SELECT u.email FROM User u")
                .getResultList();

    }



}