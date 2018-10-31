package com.codecool.lodgingsmanager.dao.implementation.database;

import com.codecool.lodgingsmanager.dao.LodgingsDao;
import com.codecool.lodgingsmanager.model.Lodgings;
import com.codecool.lodgingsmanager.util.LodgingDataField;
import com.codecool.lodgingsmanager.util.UserDataField;
import com.codecool.lodgingsmanager.util.UserType;

import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.List;

public class LodgingsDaoDb implements LodgingsDao {

    private static LodgingsDao INSTANCE = null;

    private LodgingsDaoDb() {
    }

    public static LodgingsDao getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new LodgingsDaoDb();
        }
        return INSTANCE;
    }

    @Override
    public Lodgings find(long id) throws NoResultException {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Lodgings> cq = cb.createQuery(Lodgings.class);
        Root<Lodgings> lodgingsRoot = cq.from(Lodgings.class);
        ParameterExpression<Long> pe = cb.parameter(Long.class);
        cq.select(lodgingsRoot).where(cb.equal(lodgingsRoot.get(LodgingDataField.ID.getInputString()), pe));

        TypedQuery<Lodgings> query = em.createQuery(cq);
        query.setParameter(pe, id);

        return query.getSingleResult();
    }

    @Override
    public void add(Lodgings object) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(object);
        transaction.commit();

    }

    @Override
    public void update(Lodgings object) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.merge(object);
        transaction.commit();
    }

    @Override
    public void remove(long id) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(find(id));
        transaction.commit();
    }




    @Override
    public List<Lodgings> getAll() throws NoResultException {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Lodgings> cq = cb.createQuery(Lodgings.class);
        Root<Lodgings> lodgingsRoot = cq.from(Lodgings.class);
        cq.select(lodgingsRoot);

        TypedQuery<Lodgings> query = em.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public List<Lodgings> getAllLodgingsBy(long userId) throws NoResultException {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Lodgings> cq = cb.createQuery(Lodgings.class);
        Root<Lodgings> lodgingsRoot = cq.from(Lodgings.class);
        ParameterExpression<Long> pe = cb.parameter(Long.class);
        cq.select(lodgingsRoot).where(cb.equal(lodgingsRoot.get(UserType.LANDLORD.getStringValue()).get(UserDataField.ID.getInputString()), pe));
        TypedQuery<Lodgings> query = em.createQuery(cq);
        query.setParameter(pe, userId);
        return query.getResultList();

    }

}
