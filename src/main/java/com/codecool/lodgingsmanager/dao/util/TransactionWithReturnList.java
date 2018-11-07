package com.codecool.lodgingsmanager.dao.util;

import javax.persistence.EntityManager;
import java.util.List;

@FunctionalInterface
public interface TransactionWithReturnList<E> {

    List<E> executeQueryReturnList(EntityManager em);

}
