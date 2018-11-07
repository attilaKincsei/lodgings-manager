package com.codecool.lodgingsmanager.dao.implementation.database;

import com.codecool.lodgingsmanager.dao.ToDoDao;
import com.codecool.lodgingsmanager.dao.util.EMDriver;
import com.codecool.lodgingsmanager.model.ToDo;

import javax.persistence.EntityManager;
import javax.persistence.Query;
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
        EntityManager em = EMDriver.getEntityManager();
        Query findQuery = em.createQuery("SELECT t FROM ToDo t WHERE t.id = " + id);
        ToDo result = (ToDo) findQuery.getSingleResult();
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
