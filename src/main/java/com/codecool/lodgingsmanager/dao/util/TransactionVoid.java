package com.codecool.lodgingsmanager.dao.util;

import javax.persistence.EntityManager;

@FunctionalInterface
public interface TransactionVoid {

    void executeVoidTransaction(EntityManager em);
}
