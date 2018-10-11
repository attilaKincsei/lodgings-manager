package com.codecool.lodgingsmanager.dao.implementation.database;

import com.codecool.lodgingsmanager.dao.LodgingsDao;
import com.codecool.lodgingsmanager.model.Lodgings;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class LodgingsDaoDb implements LodgingsDao {

    @Override
    public void add(Lodgings object) {
        EMDriver driver = new EMDriver();
        EntityManager driverManager = driver.getEm();
        EntityTransaction transaction = driverManager.getTransaction();
        transaction.begin();
        driverManager.persist(object);
        transaction.commit();
        System.out.println("Lodgings added");
    }

    @Override
    public Lodgings find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Lodgings> getAll() {
        return null;
    }
}
