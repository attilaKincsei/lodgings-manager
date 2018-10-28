
package com.codecool.lodgingsmanager.dao.implementation.database;

import com.codecool.lodgingsmanager.dao.UserDao;
import com.codecool.lodgingsmanager.model.User;

public class UserDaoDb<L extends User> extends UserDao<L> {

    public UserDaoDb(Class<L> classType) {
        super(classType);
    }






}