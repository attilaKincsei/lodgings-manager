package com.codecool.lodgingsmanager.dao.util;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@FunctionalInterface
public interface TransactionWithReturn<U> {

    U executeQuery(EntityManager em, CriteriaBuilder cb, CriteriaQuery<U> cq, Root<U> tRoot);

}
