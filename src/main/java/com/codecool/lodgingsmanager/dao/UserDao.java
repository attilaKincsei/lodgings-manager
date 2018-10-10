package com.codecool.lodgingsmanager.dao;

import com.codecool.lodgingsmanager.model.User;

import java.util.List;

public interface UserDao extends BaseDAO<User> {

    User findIdBy(String email);
    List<String> getAllEmailAddresses();

}
