package com.codecool.lodgingsmanager.dao;

import com.codecool.lodgingsmanager.model.Lodgings;
import com.codecool.lodgingsmanager.util.UserDataField;
import com.codecool.lodgingsmanager.util.UserType;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

public abstract class UserDao<U> extends BaseDAO<U> {

    public final Class<U> classType;

    public UserDao(Class<U> classType) {
        this.classType = classType;
    }

    @Override
    public List<U> getAll() throws NoResultException {
        // this is same as: "SELECT u FROM User u"
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<U> criteriaQuery = criteriaBuilder.createQuery(classType);
        Root<U> userRoot = criteriaQuery.from(classType);
        criteriaQuery.select(userRoot);
        TypedQuery<U> query = em.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public U find(long id) throws NoResultException {
        // this is same as: "SELECT u FROM User u WHERE u.user_id = " + id
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<U> criteriaQuery = criteriaBuilder.createQuery(classType);
        Root<U> userRoot = criteriaQuery.from(classType);
        ParameterExpression<Long> parameterExpression = criteriaBuilder.parameter(Long.class);
        criteriaQuery.select(userRoot).where(criteriaBuilder.equal(userRoot.get(UserDataField.ID.getInputString()), parameterExpression));
        TypedQuery<U> query = em.createQuery(criteriaQuery);
        query.setParameter(parameterExpression, id);

        return query.getSingleResult();
    }


    public U findIdBy(String email) throws NoResultException {
        // this is same as: "SELECT u FROM User u WHERE u.email = " + email
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<U> criteriaQuery = criteriaBuilder.createQuery(classType);
        Root<U> userRoot = criteriaQuery.from(classType);
        ParameterExpression<String> parameterExpression = criteriaBuilder.parameter(String.class);
        criteriaQuery.select(userRoot).where(criteriaBuilder.equal(userRoot.get(UserDataField.EMAIL_ADDRESS.getInputString()), parameterExpression));
        TypedQuery<U> query = em.createQuery(criteriaQuery);
        query.setParameter(parameterExpression, email);
        return query.getSingleResult();

    }

    public List<String> getAllEmailAddresses() throws NoResultException {
        // this is same as: "SELECT u.email FROM User u"
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<String> criteriaQuery = criteriaBuilder.createQuery(String.class);
        Root<U> userRoot = criteriaQuery.from(classType);
        criteriaQuery.select(userRoot.get(UserDataField.EMAIL_ADDRESS.getInputString()));
        TypedQuery<String> query = em.createQuery(criteriaQuery);
        return query.getResultList();

    }

    /*
      todo: Do this with Criteria API
      select u.*, l.id, l.name
      FROM site_user u
             LEFT JOIN lodgings l on u.user_id = l.landlord_user_id
             LEFT JOIN lodgings l2 on u.user_id = l2.propertymanager_user_id
      where l.id = 1
         or l2.id = 1;
      @param lodgingsId
     * @return
     */
//    public List<U> getAllUserBy(long lodgingsId) {
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<U> cq = cb.createQuery(classType);
//        Root<U> userRoot = cq.from(classType);
//        Root<Lodgings> lodgingsRoot = cq.from(Lodgings.class);
//        Join<U, Lodgings> lodgingsJoin = lodgingsRoot.join(UserType.LANDLORD.getStringValue(), JoinType.LEFT);
//        cq.multiselect(lodgingsJoin);
////        ParameterExpression<Long> idParameter = cb.parameter(Long.class);
////        cq.select(userRoot).where(cb.equal(userRoot.get(UserDataField.LANDLORD_LODGINGS.getInputString()).get(UserDataField.ID.getInputString()), idParameter));
//        TypedQuery<U> query = em.createQuery(cq);
////        query.setParameter(idParameter, lodgingsId);
//        return query.getResultList();
//
//    }
}
