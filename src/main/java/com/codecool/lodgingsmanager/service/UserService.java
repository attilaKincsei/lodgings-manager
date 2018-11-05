package com.codecool.lodgingsmanager.service;

import com.codecool.lodgingsmanager.dao.UserDao;
import com.codecool.lodgingsmanager.model.User;

import java.util.List;

public class UserService implements BaseService<User> {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void handleAddNew(User newUser) {
        userDao.add(newUser);
    }

    @Override
    public User handleGetUserBy(String userEmail) {
        return userDao.findIdBy(userEmail);
    }

    @Override
    public List<User> handleGetAllBy(long lodgingsId) {
        return userDao.getAllUserBy(lodgingsId);
    }

    @Override
    public void handleUpdate(User user) {
        userDao.update(user);
    }

    @Override
    public void handleDeletion(long id) {
        userDao.remove(id);
    }

    @Override
    public String handleCRUDBy(String requestPath, String userId) {
        String templateToRender;
        switch (requestPath) {
            case "/user":
                templateToRender = "view_user.html";
                break;
            case "/user/edit":
                templateToRender = "edit_user.html";
                break;
            case "/user/delete":
                handleDeletion(Long.parseLong(userId));
                templateToRender = null;
                break;
            default:
                templateToRender = "view_user.html";
                break;
        }
        return templateToRender;
    }
}
