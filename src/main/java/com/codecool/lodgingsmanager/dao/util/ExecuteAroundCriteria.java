package com.codecool.lodgingsmanager.dao.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class ExecuteAroundCriteria {

    private ExecuteAroundCriteria() {
    }

    public static void executeAroundVoid(TransactionVoid t) {
        EntityManager em = EMDriver.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        t.executeVoidTransaction(em);

        transaction.commit();
        em.close();
    }

    public static <T> T executeAroundQuerySingle(Class<T> returnType, Long id, TransactionWithReturn<T> t) {
        // this is same as: "SELECT u FROM User u WHERE u.user_id = " + id
        EntityManager em = EMDriver.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(returnType);
        Root<T> tRoot = cq.from(returnType);

        T singleResult = t.executeQuery(em, cb, cq, tRoot);

        em.close();
        return singleResult;
    }

    public static <R> List<R> executeAroundQueryMultiple(Class<R> returnType, TransactionWithReturnList<R> t) {
        EntityManager em = EMDriver.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<R> cq = cb.createQuery(returnType);
        Root<R> tRoot = cq.from(returnType);

        List<R> resultList = t.executeQueryReturnList(em, cq, tRoot);

        em.close();
        return resultList;
    }


}
