package com.codecool.lodgingsmanager.dao.util;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@FunctionalInterface
public interface TransactionWithReturnList<E> {

    List<E> executeQueryReturnList(EntityManager em, CriteriaQuery<E> cq, Root<E> tRoot);

}
