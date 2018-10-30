package com.codecool.lodgingsmanager.service;

import com.codecool.lodgingsmanager.dao.ToDoDao;
import com.codecool.lodgingsmanager.model.Lodgings;
import com.codecool.lodgingsmanager.model.ToDo;
import com.codecool.lodgingsmanager.model.User;

import java.util.List;

public class ToDoService extends BaseService<ToDo> {

    @Override
    public List<ToDo> handleGetListBy(String stringParam, long longParam) {
        // todo
        return null;
    }

    @Override
    public User handleGetUserBy(String param) {
        // todo
        return null;
    }


    @Override
    public void handleDeletion(long id) {
        // todo
    }

    @Override
    public List<Lodgings> handleGetAllLodgingsBy(long userId) {
        return null;
    }

    @Override
    public void handleUpdate(ToDo user) {
        // todo
    }

    @Override
    public void injectDependency(BaseService handler) {
        // todo
    }

    @Override
    public void handleAddNew(ToDo object) {
        // todo
    }

    @Override
    public List<ToDo> handleGetListBy(long id) {
        // todo
        return null;
    }
}
