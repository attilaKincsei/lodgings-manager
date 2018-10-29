package com.codecool.lodgingsmanager.service;

import com.codecool.lodgingsmanager.dao.ToDoDao;
import com.codecool.lodgingsmanager.model.Lodgings;
import com.codecool.lodgingsmanager.model.ToDo;
import com.codecool.lodgingsmanager.model.User;

import java.util.List;

public class ToDoService extends BaseService<ToDo> {

    @Override
    public List<ToDo> handleBy(String stringParam, long longParam) {
        return null;
    }

    @Override
    public ToDo handleBy(String param) {
        return null;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public void handleAddNewLodgings(Lodgings newLodgings) {

    }

    @Override
    public List<Lodgings> handleGettingLodgingsBy(User user) {
        return null;
    }

    @Override
    public void handleAddNewUser(User newUser) {

    }

    @Override
    public void handleUpdate(ToDo user) {

    }
}
