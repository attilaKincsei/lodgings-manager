package com.codecool.lodgingsmanager.dao.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class ExecuteAroundVoid {

    private ExecuteAroundVoid() {
    }

    public static void manageDML(TransactionVoid t) {
        EntityManager em = EMDriver.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        t.executeVoidTransaction(em);
        transaction.commit();
        em.close();
    }
}
