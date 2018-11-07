package com.codecool.lodgingsmanager.dao.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class ExecuteAroundReturnList {

    public static <R> List<R> manageQueryingMultiple(TransactionWithReturnList<R> t) {
        EntityManager em = EMDriver.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        List<R> result = t.executeQueryReturnList(em);
        transaction.commit();
        em.close();
        return result;
    }


}
