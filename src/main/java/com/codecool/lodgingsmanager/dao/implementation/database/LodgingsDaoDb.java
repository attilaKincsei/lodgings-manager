package com.codecool.lodgingsmanager.dao.implementation.database;

import com.codecool.lodgingsmanager.dao.LodgingsDao;
import com.codecool.lodgingsmanager.model.Landlord;
import com.codecool.lodgingsmanager.model.Lodgings;
import com.codecool.lodgingsmanager.util.LodgingDataField;
import com.codecool.lodgingsmanager.util.UserDataField;
import com.codecool.lodgingsmanager.util.UserType;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.List;

public class LodgingsDaoDb extends LodgingsDao<Lodgings> {

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
