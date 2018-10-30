package com.codecool.lodgingsmanager.util;

import com.codecool.lodgingsmanager.dao.UserDao;
import com.codecool.lodgingsmanager.dao.implementation.database.UserDaoDb;
import com.codecool.lodgingsmanager.model.Lodgings;
import com.codecool.lodgingsmanager.model.User;
import org.mindrot.jbcrypt.BCrypt;

import javax.persistence.NoResultException;

public class PasswordHashing {

    private static final UserDao userDataManager = UserDaoDb.getINSTANCE();
    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }

    public static User checkPassword(String candidate, String email) throws NoResultException {

        User user = userDataManager.findIdBy(email);
        String hashed = user.getPasswordHash();

        if (BCrypt.checkpw(candidate, hashed)) {
            return user;
        } else {
            return null;
        }


    }

}
