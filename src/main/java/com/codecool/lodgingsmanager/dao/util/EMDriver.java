package com.codecool.lodgingsmanager.dao.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EMDriver {

    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("lodgingsmanagerPU");

    private EMDriver() {
    }

    public static EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
}
