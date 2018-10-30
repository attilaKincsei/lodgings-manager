
package com.codecool.lodgingsmanager.dao.implementation.database;

import com.codecool.lodgingsmanager.dao.UserDao;
import com.codecool.lodgingsmanager.model.Lodgings;
import com.codecool.lodgingsmanager.model.User;
import com.codecool.lodgingsmanager.util.UserDataField;
import com.codecool.lodgingsmanager.util.UserType;

import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

public class UserDaoDb implements UserDao {

    private final Class<User> classType = User.class;

    private static UserDao INSTANCE = null;

    UserDaoDb() {
    }

    public static UserDao getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new UserDaoDb();
        }
        return INSTANCE;
    }

    @Override
    public void add(User object) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(object);
        transaction.commit();
    }
    @Override
    public void update(User object) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.merge(object);
        transaction.commit();
    }

    @Override
    public void remove(long id) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(find(id));
        transaction.commit();
    }

    @Override
    public List<User> getAll() throws NoResultException {
        // this is same as: "SELECT u FROM User u"
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(classType);
        Root<User> userRoot = criteriaQuery.from(classType);
        criteriaQuery.select(userRoot);
        TypedQuery<User> query = em.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public User find(long id) throws NoResultException {
        // this is same as: "SELECT u FROM User u WHERE u.user_id = " + id
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(classType);
        Root<User> userRoot = criteriaQuery.from(classType);
        ParameterExpression<Long> parameterExpression = criteriaBuilder.parameter(Long.class);
        criteriaQuery.select(userRoot).where(criteriaBuilder.equal(userRoot.get(UserDataField.ID.getInputString()), parameterExpression));
        TypedQuery<User> query = em.createQuery(criteriaQuery);
        query.setParameter(parameterExpression, id);

        return query.getSingleResult();
    }

    @Override
    public User findIdBy(String email) throws NoResultException {
        // this is same as: "SELECT u FROM User u WHERE u.email = " + email
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(classType);
        Root<User> userRoot = criteriaQuery.from(classType);
        ParameterExpression<String> parameterExpression = criteriaBuilder.parameter(String.class);
        criteriaQuery.select(userRoot).where(criteriaBuilder.equal(userRoot.get(UserDataField.EMAIL_ADDRESS.getInputString()), parameterExpression));
        TypedQuery<User> query = em.createQuery(criteriaQuery);
        query.setParameter(parameterExpression, email);
        return query.getSingleResult();

    }

    @Override
    public List<String> getAllEmailAddresses() throws NoResultException {
        // this is same as: "SELECT u.email FROM User u"
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<String> criteriaQuery = criteriaBuilder.createQuery(String.class);
        Root<User> userRoot = criteriaQuery.from(classType);
        criteriaQuery.select(userRoot.get(UserDataField.EMAIL_ADDRESS.getInputString()));
        TypedQuery<String> query = em.createQuery(criteriaQuery);
        return query.getResultList();

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

        return allUserList;

    }


}