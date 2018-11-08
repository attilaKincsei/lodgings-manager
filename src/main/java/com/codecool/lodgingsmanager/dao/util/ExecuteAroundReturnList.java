package com.codecool.lodgingsmanager.dao.util;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class ExecuteAroundReturnList {

    public static <R> List<R> manageQueryingMultiple(Class<R> returnType, TransactionWithReturnList<R> t) {
        EntityManager em = EMDriver.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<R> cq = cb.createQuery(returnType);
        Root<R> tRoot = cq.from(returnType);

        List<R> resultList = t.executeQueryReturnList(em, cq, tRoot);

        em.close();
        return resultList;


    }


}
