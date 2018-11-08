package com.codecool.lodgingsmanager.dao.implementation.database;

import com.codecool.lodgingsmanager.dao.BaseDAO;
import com.codecool.lodgingsmanager.dao.util.ExecuteAroundCriteria;
import com.codecool.lodgingsmanager.util.FieldType;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.List;

public abstract class BaseDaoDb<T> implements BaseDAO<T> {

    Class<T> classType;

    BaseDaoDb(Class<T> classType) {
        this.classType = classType;
    }

    @Override
    public void add(T object) {
        ExecuteAroundCriteria.executeAroundVoid(em -> em.persist(object));
    }
    @Override
    public void update(T object) {
        ExecuteAroundCriteria.executeAroundVoid(em -> em.merge(object));
    }

    @Override
    public void remove(long id) {
        ExecuteAroundCriteria.executeAroundVoid(em -> removeFunction(id, em));
    }

    private void removeFunction(long id, EntityManager em) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = cb.createQuery(classType);
        Root<T> userRoot = criteriaQuery.from(classType);
        ParameterExpression<Long> pe = cb.parameter(Long.class);
        criteriaQuery.select(userRoot).where(cb.equal(userRoot.get(FieldType.ID.getInputString()), pe));
        TypedQuery<T> query = em.createQuery(criteriaQuery);
        query.setParameter(pe, id);
        T singleResult = query.getSingleResult();
        em.remove(singleResult);
    }

    @Override
    public T find(long id) throws NoResultException {
        // this is same as: "SELECT u FROM T u WHERE u.id = " + id
        return ExecuteAroundCriteria.executeAroundQuerySingle(classType, id, ((em, cb, cq, tRoot) -> findFunction(id, em, cb, cq, tRoot)));

    }

    private T findFunction(long id, EntityManager em, CriteriaBuilder cb, CriteriaQuery<T> cq, Root<T> tRoot) {
        ParameterExpression<Long> pe = cb.parameter(Long.class);
        cq.select(tRoot).where(cb.equal(tRoot.get(FieldType.ID.getInputString()), pe));
        TypedQuery<T> query = em.createQuery(cq);
        query.setParameter(pe, id);
        return query.getSingleResult();
    }

    @Override
    public List<T> getAll() throws NoResultException {
        // this is same as: "SELECT u FROM T u"
        return ExecuteAroundCriteria.executeAroundQueryMultiple(classType, this::getAllFunction);

    }

    private List<T> getAllFunction(EntityManager em, CriteriaQuery<T> cq, Root<T> tRoot) {
        cq.select(tRoot);
        TypedQuery<T> query = em.createQuery(cq);
        return query.getResultList();
    }
}
