package com.codecool.lodgingsmanager.service;

import com.codecool.lodgingsmanager.dao.ToDoDao;
import com.codecool.lodgingsmanager.model.Lodgings;
import com.codecool.lodgingsmanager.model.ToDo;
import com.codecool.lodgingsmanager.model.User;

import java.util.List;

public class ToDoService extends BaseService<ToDo> {

    @Override
    public List<ToDo> handleGetListBy(String stringParam, long longParam) {
        return null;
    }

    @Override
    public ToDo handleGetSingleObjectBy(String param) {
        return null;
    }

    @Override
    public void handleDelete(long id) {

    }

    @Override
    public void handleUpdate(ToDo user) {

    }

    @Override
    public void handleAddNew(ToDo object) {

    }

    @Override
    public List<ToDo> handleGetListBy(long id) {
        return null;
    }
}
