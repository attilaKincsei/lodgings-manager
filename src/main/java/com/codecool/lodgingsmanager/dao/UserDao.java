package com.codecool.lodgingsmanager.dao;

import com.codecool.lodgingsmanager.model.User;

import java.util.List;

public abstract class UserDao extends BaseDAO<User> {

    public abstract User findIdBy(String email);
    public abstract List<String> getAllEmailAddresses();
}
