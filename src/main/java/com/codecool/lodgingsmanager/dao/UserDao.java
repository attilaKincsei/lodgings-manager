package com.codecool.lodgingsmanager.dao;

import com.codecool.lodgingsmanager.model.User;

import javax.persistence.NoResultException;
import java.util.List;

public interface UserDao extends BaseDAO<User> {

    User findIdBy(String email) throws NoResultException;
    List<String> getAllEmailAddresses() throws NoResultException;
    List<User> getAllUserBy(long lodgingsId);
}
