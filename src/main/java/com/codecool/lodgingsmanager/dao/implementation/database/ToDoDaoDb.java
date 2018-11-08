package com.codecool.lodgingsmanager.dao.implementation.database;

import com.codecool.lodgingsmanager.dao.ToDoDao;
import com.codecool.lodgingsmanager.dao.util.EMDriver;
import com.codecool.lodgingsmanager.model.Lodgings;
import com.codecool.lodgingsmanager.model.ToDo;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class ToDoDaoDb extends BaseDaoDb<ToDo> implements ToDoDao {

    private static ToDoDao INSTANCE = null;

    private ToDoDaoDb() {
        super(ToDo.class);
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
        return result;
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

    @Override
    public List<ToDo> getAllTodosBy(List<Lodgings> lodgingsList) {
        List<ToDo> resultList = new ArrayList();
        for (Lodgings lodgings : lodgingsList) {
            resultList.addAll(getAllTodosBy(lodgings.getId()));
        }
        return resultList;
    }

    @Override
    public List<ToDo> getAllTodosBy(long lodgingsId) {
        EntityManager em = EMDriver.getEntityManager();
        Query findQuery = em.createQuery("SELECT t FROM ToDo t WHERE t.lodgings = " + lodgingsId);
        return findQuery.getResultList();
    }
}
