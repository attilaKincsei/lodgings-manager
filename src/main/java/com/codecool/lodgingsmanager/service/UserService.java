package com.codecool.lodgingsmanager.service;

import com.codecool.lodgingsmanager.dao.UserDao;
import com.codecool.lodgingsmanager.model.Lodgings;
import com.codecool.lodgingsmanager.model.User;
import com.codecool.lodgingsmanager.model.builder.AddressBuilder;
import com.codecool.lodgingsmanager.util.PasswordHashing;

import javax.persistence.NoResultException;
import java.util.List;

public class UserService implements BaseService<User> {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User handleGetUserBy(String userEmail) throws NoResultException {
        return userDao.findIdBy(userEmail);
    }

    @Override
    public List<User> handleGetAllBy(long lodgingsId) {
        return userDao.getAllUserBy(lodgingsId);
    }

    @Override
    public void handleDeletion(long id) {
        userDao.remove(id);
    }

    @Override
    public String handleCrudGetBy(String requestPath, String userId) {
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

    @Override
    public List<String> getEnumAsStringList() { // todo: implement
        return null;
    }

    @Override
    public void handleAddPost(User newUser) {
        userDao.add(newUser);
    }

    public boolean handleEditPost(
            String userEmail, String firstName, String surname, String oldPassword, String newPassword,
            String phoneNumber, AddressBuilder fullAddress) {


        boolean isOldPasswordCorrect = PasswordHashing.isPasswordCorrect(oldPassword, userEmail);
        if (isOldPasswordCorrect) {

            User user = handleGetUserBy(userEmail);

            if (!newPassword.equals("")) {
                user.setPasswordHash(PasswordHashing.hashPassword(newPassword));
            }

            user.setFirstName(firstName);
            user.setSurname(surname);
            user.setPhoneNumber(phoneNumber);
            user.setFullAddress(fullAddress);
            userDao.update(user);
        }

        return isOldPasswordCorrect;

    }

}
