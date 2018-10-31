package com.codecool.lodgingsmanager.dao.implementation.database;

import com.codecool.lodgingsmanager.dao.ToDoDao;
import com.codecool.lodgingsmanager.model.ToDo;

import java.util.List;

public class ToDoDaoDb implements ToDoDao {

    private static ToDoDao INSTANCE = null;

    private ToDoDaoDb() {
    }

    public static ToDoDao getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new ToDoDaoDb();
        }
        return INSTANCE;
    }



    @Override
    public ToDo find(long id) {
        // todo
        return null;
    }

    @Override
    public void add(ToDo object) {
        // todo
    }

    @Override
    public void update(ToDo object) {
        // todo
    }

    @Override
    public void remove(long id) {
        // todo
    }

    @Override
    public List<ToDo> getAll() {
        // todo
        return null;
    }
}
