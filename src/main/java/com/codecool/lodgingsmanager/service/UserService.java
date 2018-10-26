package com.codecool.lodgingsmanager.service;

import com.codecool.lodgingsmanager.dao.LodgingsDao;
import com.codecool.lodgingsmanager.dao.UserDao;
import com.codecool.lodgingsmanager.dao.implementation.database.LodgingsDaoDb;
import com.codecool.lodgingsmanager.dao.implementation.database.UserDaoDb;
import com.codecool.lodgingsmanager.model.Lodgings;
import com.codecool.lodgingsmanager.model.User;

import java.util.List;

public class UserService extends BaseService<User> {

    UserDao userDataManager = new UserDaoDb();
    LodgingsDao lodgingsDataManager = new LodgingsDaoDb();


    @Override
    public List<User> handleBy(String stringParam, long longParam) {
        return null;
    }

    @Override
    public User handleBy(String userEmail) {
        return userDataManager.findIdBy(userEmail);
    }

    @Override
    public void delete(long id) {
        List<Lodgings> lodgingsBy = lodgingsDataManager.getAllLodgingsBy(id);
        for (Lodgings lodgings : lodgingsBy) {
            lodgingsDataManager.remove(lodgings.getId());
        }
        userDataManager.remove(id);
    }



}
