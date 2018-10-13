package com.codecool.lodgingsmanager.dao.implementation.database;

import com.codecool.lodgingsmanager.dao.LodgingsDao;
import com.codecool.lodgingsmanager.model.Lodgings;
import com.codecool.lodgingsmanager.model.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class LodgingsDaoDb implements LodgingsDao {

    @Override
    public void add(Lodgings object) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(object);
        transaction.commit();
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

    @Override
    public List<Lodgings> getAllLodgingsBy(int userId) {
        return em.createQuery("SELECT l FROM Lodgings l WHERE l.landlord.id = " + userId).getResultList();

    }

}
