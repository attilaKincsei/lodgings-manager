package com.codecool.lodgingsmanager.util;

import com.codecool.lodgingsmanager.dao.UserDao;
import com.codecool.lodgingsmanager.dao.implementation.database.UserDaoDb;
import com.codecool.lodgingsmanager.model.Lodgings;
import com.codecool.lodgingsmanager.model.User;
import org.mindrot.jbcrypt.BCrypt;

import javax.persistence.NoResultException;

public class PasswordHashing {



    private final static UserDao userDataManager = new UserDaoDb();

    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }

    public static boolean isPasswordCorrect(String candidate, String email) {
        User user = userDataManager.findIdBy(email);
        String hashed = user.getPasswordHash();

        return BCrypt.checkpw(candidate, hashed);
    }

}
