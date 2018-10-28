package com.codecool.lodgingsmanager.dao.implementation.database;

import com.codecool.lodgingsmanager.dao.LodgingsDao;
import com.codecool.lodgingsmanager.model.Lodgings;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class LodgingsDaoDb extends LodgingsDao {

    @Override
    public Lodgings find(long id) throws NoResultException {
        return em.find(Lodgings.class, id);
    }


    @Override
    public List<Lodgings> getAll() throws NoResultException {
        return em.createQuery("SELECT l FROM Lodgings l", Lodgings.class).getResultList();
    }

    @Override
    public List<Lodgings> getAllLodgingsBy(long userId) throws NoResultException {
        TypedQuery<Lodgings> query = em.createQuery("SELECT l FROM Lodgings l WHERE l.landlord.id = ?1", Lodgings.class);
        return query.setParameter(1, userId).getResultList();

    }

}
