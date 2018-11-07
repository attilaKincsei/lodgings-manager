package com.codecool.lodgingsmanager.dao.util;

import com.codecool.lodgingsmanager.util.FieldType;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

public class ExecuteAroundReturn {

    private ExecuteAroundReturn() {
    }

    public static <T> T manageQuerying(Class<T> returnType, Long id, TransactionWithReturn<T> t) {
        // this is same as: "SELECT u FROM User u WHERE u.user_id = " + id
        EntityManager em = EMDriver.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(returnType);
        Root<T> tRoot = cq.from(returnType);

        T singleResult = t.executeQuery(em, cb, cq, tRoot);

        em.close();
        return singleResult;


    }
}
