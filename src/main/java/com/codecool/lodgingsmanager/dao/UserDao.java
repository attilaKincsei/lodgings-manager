package com.codecool.lodgingsmanager.dao;

import com.codecool.lodgingsmanager.util.UserDataField;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public abstract class UserDao<U> extends BaseDAO<U> {

    public final Class<U> classType;

    public UserDao(Class<U> classType) {
        this.classType = classType;
    }

    @Override
    public List<U> getAll() throws NoResultException {
        // this is same as: "SELECT t FROM User t"
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
        criteriaQuery.select(userRoot).where(criteriaBuilder.equal(userRoot.get(UserDataField.ID.getInputString()), id));

        TypedQuery<U> query = em.createQuery(criteriaQuery);

        return query.getSingleResult();
    }


    public U findIdBy(String email) throws NoResultException {
        // this is same as: "SELECT u FROM User u WHERE u.email = " + email
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<U> criteriaQuery = criteriaBuilder.createQuery(classType);
        Root<U> userRoot = criteriaQuery.from(classType);
        criteriaQuery.select(userRoot).where(criteriaBuilder.equal(userRoot.get(UserDataField.EMAIL_ADDRESS.getInputString()), email));
        TypedQuery<U> query = em.createQuery(criteriaQuery);
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

}
