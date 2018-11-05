
package com.codecool.lodgingsmanager.dao.implementation.database;

import com.codecool.lodgingsmanager.dao.UserDao;
import com.codecool.lodgingsmanager.dao.util.EMDriver;
import com.codecool.lodgingsmanager.model.Lodgings;
import com.codecool.lodgingsmanager.model.User;
import com.codecool.lodgingsmanager.util.UserDataField;
import com.codecool.lodgingsmanager.util.UserType;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.util.List;

public class UserDaoDb extends BaseDaoDb<User> implements UserDao {

    private static UserDao INSTANCE = null;

    UserDaoDb() {
        super(User.class);
    }

    public static UserDao getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new UserDaoDb();
        }
        return INSTANCE;
    }

    @Override
    public void remove(long id) {
        EntityManager em = EMDriver.getEntityManager();
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(classType);
        Root<User> userRoot = criteriaQuery.from(classType);
        ParameterExpression<Long> parameterExpression = criteriaBuilder.parameter(Long.class);
        criteriaQuery.select(userRoot).where(criteriaBuilder.equal(userRoot.get(UserDataField.ID.getInputString()), parameterExpression));
        TypedQuery<User> query = em.createQuery(criteriaQuery);
        query.setParameter(parameterExpression, id);
        User singleResult = query.getSingleResult();

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(singleResult);
        transaction.commit();
        em.close();
    }

    @Override
    public List<User> getAll() throws NoResultException {
        // this is same as: "SELECT u FROM User u"
        EntityManager em = EMDriver.getEntityManager();
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(classType);
        Root<User> userRoot = criteriaQuery.from(classType);
        criteriaQuery.select(userRoot);
        TypedQuery<User> query = em.createQuery(criteriaQuery);
        List<User> resultList = query.getResultList();
        em.close();
        return resultList;
    }

    @Override
    public User find(long id) throws NoResultException {
        // this is same as: "SELECT u FROM User u WHERE u.user_id = " + id
        EntityManager em = EMDriver.getEntityManager();
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(classType);
        Root<User> userRoot = criteriaQuery.from(classType);
        ParameterExpression<Long> parameterExpression = criteriaBuilder.parameter(Long.class);
        criteriaQuery.select(userRoot).where(criteriaBuilder.equal(userRoot.get(UserDataField.ID.getInputString()), parameterExpression));
        TypedQuery<User> query = em.createQuery(criteriaQuery);
        query.setParameter(parameterExpression, id);
        User singleResult = query.getSingleResult();
        em.close();
        return singleResult;
    }

    @Override
    public User findIdBy(String email) throws NoResultException {
        // this is same as: "SELECT u FROM User u WHERE u.email = " + email
        EntityManager em = EMDriver.getEntityManager();
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(classType);
        Root<User> userRoot = criteriaQuery.from(classType);
        ParameterExpression<String> parameterExpression = criteriaBuilder.parameter(String.class);
        criteriaQuery.select(userRoot).where(criteriaBuilder.equal(userRoot.get(UserDataField.EMAIL_ADDRESS.getInputString()), parameterExpression));
        TypedQuery<User> query = em.createQuery(criteriaQuery);
        query.setParameter(parameterExpression, email);
        User singleResult = query.getSingleResult();
        em.close();
        return singleResult;
    }

    @Override
    public List<String> getAllEmailAddresses() throws NoResultException {
        // this is same as: "SELECT u.email FROM User u"
        EntityManager em = EMDriver.getEntityManager();
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<String> criteriaQuery = criteriaBuilder.createQuery(String.class);
        Root<User> userRoot = criteriaQuery.from(classType);
        criteriaQuery.select(userRoot.get(UserDataField.EMAIL_ADDRESS.getInputString()));
        TypedQuery<String> query = em.createQuery(criteriaQuery);
        List<String> resultList = query.getResultList();
        em.close();
        return resultList;

    }

    /*
      select u.*, l.id, l.name
      FROM site_user u
             LEFT JOIN lodgings l on u.user_id = l.landlord_user_id
             LEFT JOIN lodgings l2 on u.user_id = l2.propertymanager_user_id
      where l.id = 1
         or l2.id = 1;
      @param lodgingsId
     * @return
     */
    @Override
    public List<User> getAllUserBy(long lodgingsId) {
        EntityManager em = EMDriver.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(classType);

        Root<Lodgings> lodgingsRoot = cq.from(Lodgings.class);
        Join<Lodgings, User> landlord = lodgingsRoot.join(UserType.LANDLORD.getStringValue(), JoinType.INNER);
        ParameterExpression<Long> idParameter = cb.parameter(Long.class);
        cq.select(landlord).where(cb.equal(lodgingsRoot.get("id"), idParameter));
        TypedQuery<User> query = em.createQuery(cq);
        query.setParameter(idParameter, lodgingsId);

        List<User> allUserList = query.getResultList();

        Join<Lodgings, User> propertyManager = lodgingsRoot.join(UserType.PROPERTY_MANAGER.getStringValue(), JoinType.INNER);
        cq.select(propertyManager).where(cb.equal(lodgingsRoot.get("id"), idParameter));
        TypedQuery<User> query2 = em.createQuery(cq);
        query2.setParameter(idParameter, lodgingsId);
        allUserList.addAll(query2.getResultList());
        em.close();
        return allUserList;

    }


}