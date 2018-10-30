package com.codecool.lodgingsmanager.service.ajax;

import com.codecool.lodgingsmanager.model.User;
import com.codecool.lodgingsmanager.service.BaseService;

import javax.persistence.NoResultException;

public class EmailCheckerService {

    private final BaseService<User> userHandler;

    public EmailCheckerService(BaseService<User> userHandler) {
        this.userHandler = userHandler;
    }

    public String checkIfEmailRegistered(String emailentered) {
        String registeredEmail = "";
        try {
            User mightBeRegisteredUser = userHandler.handleGetSingleObjectBy(emailentered);
            registeredEmail = mightBeRegisteredUser.getEmail();
        } catch (NoResultException nre) {
            // todo: change this to logger?
            System.out.println(emailentered + " is not yet registered in database");
        }
        return registeredEmail;
    }


}
