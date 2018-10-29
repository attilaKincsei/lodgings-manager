package com.codecool.lodgingsmanager.service.ajax;

import com.codecool.lodgingsmanager.dao.UserDao;
import com.codecool.lodgingsmanager.model.User;

import javax.persistence.NoResultException;

public class EmailCheckerService {

    private final UserDao<User> userDataManager;

    public EmailCheckerService(UserDao<User> userDataManager) {
        this.userDataManager = userDataManager;
    }

    public String checkIfEmailRegistered(String emailentered) {
        String registeredEmail = "";
        try {
            User mightBeRegisteredUser = userDataManager.findIdBy(emailentered);
            registeredEmail = mightBeRegisteredUser.getEmail();
        } catch (NoResultException nre) {
            // todo: change this to logger?
            System.out.println(emailentered + " is not yet registered in database");
        }
        return registeredEmail;
    }


}
