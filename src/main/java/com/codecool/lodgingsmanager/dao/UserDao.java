package com.codecool.lodgingsmanager.dao;

import com.codecool.lodgingsmanager.model.User;

public interface UserDao extends BaseDAO<User> {

    User findIdBy(String email);

}
