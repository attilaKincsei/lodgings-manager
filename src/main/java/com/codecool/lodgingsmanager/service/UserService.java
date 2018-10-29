package com.codecool.lodgingsmanager.service;

import com.codecool.lodgingsmanager.dao.LodgingsDao;
import com.codecool.lodgingsmanager.dao.UserDao;
import com.codecool.lodgingsmanager.model.Lodgings;
import com.codecool.lodgingsmanager.model.User;

import java.util.List;

public class UserService extends BaseService<User> {

    private final UserDao<User> userDataManager;
    private final LodgingsDao<Lodgings> lodgingsDataManager;

    public UserService(UserDao<User> userDataManager, LodgingsDao<Lodgings> lodgingsDataManager) {
        this.userDataManager = userDataManager;
        this.lodgingsDataManager = lodgingsDataManager;
    }

    @Override
    public void handleAddNew(User newUser) {
        userDataManager.add(newUser);
    }

    @Override
    public User handleGetSingleObjectBy(String userEmail) {
        return userDataManager.findIdBy(userEmail);
    }

    @Override
    public List<User> handleGetListBy(long lodgingsId) {
        return null; // userDataManager.getAllUserBy(lodgingsId);
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
    public void handleDelete(long id) {
        List<Lodgings> lodgingsBy = lodgingsDataManager.getAllLodgingsBy(id);
        for (Lodgings lodgings : lodgingsBy) {
            lodgingsDataManager.remove(lodgings.getId());
        }
        userDataManager.remove(id);
    }


}
