package com.codecool.lodgingsmanager.dao.implementation.database;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EMDriver {

    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("lodgingsmanagerPU");
    private static final EntityManager entityManager = entityManagerFactory.createEntityManager();


    private EMDriver() {
    }

    public static EntityManager getEntityManager() {
        return entityManager;
    }
}
