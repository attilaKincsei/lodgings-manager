package com.codecool.lodgingsmanager.dao.implementation.database;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EMDriver {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("lodgingsmanagerPU");
    private EntityManager em = emf.createEntityManager();

    public EntityManager getEm() {
            return em;
        }
}
