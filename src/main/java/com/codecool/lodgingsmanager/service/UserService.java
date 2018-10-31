package com.codecool.lodgingsmanager.service;

import com.codecool.lodgingsmanager.dao.UserDao;
import com.codecool.lodgingsmanager.dao.implementation.database.UserDaoDb;
import com.codecool.lodgingsmanager.model.Lodgings;
import com.codecool.lodgingsmanager.model.User;

import java.util.List;

public class UserService implements BaseService<User> {

    private final UserDao userDao = UserDaoDb.getINSTANCE();

    private BaseService<Lodgings> lodgingsHandler = null;

    @Override
    public void injectDependency(BaseService lodgingsHandler) {
        if (lodgingsHandler instanceof LodgingsService) {
            this.lodgingsHandler = (LodgingsService) lodgingsHandler;
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void handleAddNew(User newUser) {
        userDao.add(newUser);
    }

    @Override
    public User handleGetUserBy(String userEmail) {
        return userDao.findIdBy(userEmail);
    }

    @Override
    public List<User> handleGetAllBy(long lodgingsId) {
        return userDao.getAllUserBy(lodgingsId);
    }

    @Override
    public void handleUpdate(User user) {
        userDao.update(user);
    }

    @Override
    public void handleDeletion(long id) {
        List<Lodgings> lodgingsBy = ((LodgingsService) lodgingsHandler).handleGetAllLodgingsBy(id);
        for (Lodgings lodgings : lodgingsBy) {
            lodgingsHandler.handleDeletion(lodgings.getId());
        }
        userDao.remove(id);
    }


}
