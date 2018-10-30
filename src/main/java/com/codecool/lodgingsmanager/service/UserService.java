package com.codecool.lodgingsmanager.service;

import com.codecool.lodgingsmanager.dao.UserDao;
import com.codecool.lodgingsmanager.dao.implementation.database.UserDaoDb;
import com.codecool.lodgingsmanager.model.Lodgings;
import com.codecool.lodgingsmanager.model.User;

import java.util.List;

public class UserService extends BaseService<User> {

    private final UserDao userDataManager = UserDaoDb.getINSTANCE();

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
        userDataManager.add(newUser);
    }

    @Override
    public User handleGetUserBy(String userEmail) {
        return userDataManager.findIdBy(userEmail);
    }

    @Override
    public List<User> handleGetListBy(long lodgingsId) {
        return userDataManager.getAllUserBy(lodgingsId);
    }

    @Override
    public List<User> handleGetListBy(String stringParam, long longParam) {
        return null; // todo
    }

    @Override
    public void handleUpdate(User user) {
        userDataManager.update(user);
    }

    @Override
    public void handleDeletion(long id) {
        List<Lodgings> lodgingsBy = handleGetAllLodgingsBy(id);
        for (Lodgings lodgings : lodgingsBy) {
            lodgingsHandler.handleDeletion(lodgings.getId());
        }
        userDataManager.remove(id);
    }

    @Override
    public List<Lodgings> handleGetAllLodgingsBy(long userId) {
        return lodgingsHandler.handleGetAllLodgingsBy(userId);
    }


}
