package com.codecool.lodgingsmanager.dao.implementation.database;

import com.codecool.lodgingsmanager.dao.LodgingsDao;
import com.codecool.lodgingsmanager.model.Lodgings;

import javax.persistence.NoResultException;
import java.util.List;

public class LodgingsDaoDb extends LodgingsDao {

    @Override
    public Lodgings find(int id) {
        return null;
    }

    @Override
    public List<Lodgings> getAll() {
        return null;
    }

    @Override
    public List<Lodgings> getAllLodgingsBy(int userId) throws NoResultException {
        return em.createQuery("SELECT l FROM Lodgings l WHERE l.landlord.id = " + userId).getResultList();

    }

}
