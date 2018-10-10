package com.codecool.lodgingsmanager.util;

import com.codecool.lodgingsmanager.dao.UserDao;
import com.codecool.lodgingsmanager.dao.implementation.database.UserDaoDb;
import com.codecool.lodgingsmanager.model.User;
import org.mindrot.jbcrypt.BCrypt;

public class PasswordHashing {


    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }

    public static User checkPassword(String candidate, String email) {
        UserDao userDataManager = new UserDaoDb();
        User user = userDataManager.findIdBy(email);
        String hashed = user.getPasswordHash();

        if (BCrypt.checkpw(candidate, hashed)) {
            return user;
        } else {
            return null;
        }


    }

}
