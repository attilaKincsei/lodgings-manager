package com.codecool.lodgingsmanager.dao.implementation.database;

import com.codecool.lodgingsmanager.dao.LodgingsDao;
import com.codecool.lodgingsmanager.dao.util.EMDriver;
import com.codecool.lodgingsmanager.model.Lodgings;
import com.codecool.lodgingsmanager.util.LodgingDataField;
import com.codecool.lodgingsmanager.util.UserDataField;
import com.codecool.lodgingsmanager.util.UserType;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.List;

public class LodgingsDaoDb extends BaseDaoDb<Lodgings> implements LodgingsDao {

    private static LodgingsDao INSTANCE = null;

    private LodgingsDaoDb() {
        super(Lodgings.class);
    }

    public static LodgingsDao getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new LodgingsDaoDb();
        }
        return INSTANCE;
    }

    @Override
    public Lodgings find(long id) throws NoResultException {
        EntityManager em = EMDriver.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Lodgings> cq = cb.createQuery(classType);
        Root<Lodgings> lodgingsRoot = cq.from(classType);
        ParameterExpression<Long> pe = cb.parameter(Long.class);
        cq.select(lodgingsRoot).where(cb.equal(lodgingsRoot.get(LodgingDataField.ID.getInputString()), pe));

        TypedQuery<Lodgings> query = em.createQuery(cq);
        query.setParameter(pe, id);

        Lodgings singleResult = query.getSingleResult();
        em.close();
        return singleResult;
    }

    @Override
    public void remove(long id) {
        EntityManager em = EMDriver.getEntityManager();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Lodgings> cq = cb.createQuery(Lodgings.class);
        Root<Lodgings> lodgingsRoot = cq.from(Lodgings.class);
        ParameterExpression<Long> pe = cb.parameter(Long.class);
        cq.select(lodgingsRoot).where(cb.equal(lodgingsRoot.get(LodgingDataField.ID.getInputString()), pe));
        TypedQuery<Lodgings> query = em.createQuery(cq);
        query.setParameter(pe, id);
        Lodgings singleResult = query.getSingleResult();

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(singleResult);
        transaction.commit();
        em.close();
    }

    @Override
    public List<Lodgings> getAll() throws NoResultException {
        EntityManager em = EMDriver.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Lodgings> cq = cb.createQuery(Lodgings.class);
        Root<Lodgings> lodgingsRoot = cq.from(Lodgings.class);
        cq.select(lodgingsRoot);
        TypedQuery<Lodgings> query = em.createQuery(cq);
        List<Lodgings> resultList = query.getResultList();
        em.close();
        return resultList;

    }

    @Override
    public List<Lodgings> getAllLodgingsBy(long userId) throws NoResultException {
        EntityManager em = EMDriver.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Lodgings> cq = cb.createQuery(Lodgings.class);
        Root<Lodgings> lodgingsRoot = cq.from(Lodgings.class);
        ParameterExpression<Long> pe = cb.parameter(Long.class);
        cq.select(lodgingsRoot).where(cb.equal(lodgingsRoot.get(UserType.LANDLORD.getStringValue()).get(UserDataField.ID.getInputString()), pe));
        TypedQuery<Lodgings> query = em.createQuery(cq);
        query.setParameter(pe, userId);
        List<Lodgings> resultList = query.getResultList();
        em.close();
        return resultList;
    }

}
