package com.codecool.lodgingsmanager.service;

import com.codecool.lodgingsmanager.dao.UserDao;
import com.codecool.lodgingsmanager.model.User;
import com.codecool.lodgingsmanager.util.PasswordHashing;

import javax.persistence.NoResultException;
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
    public boolean handleAddAndEditPost(
            String userEmail, String firstName, String surname, String oldPassword, String newPassword,
            String phoneNumber, String country, String city, String zipCode, String address,
            String requestPath, String fake1, String fake2, String fake3, String param15) {

        User user = handleGetUserBy(userEmail);

        boolean isOldPasswordCorrect = PasswordHashing.isPasswordCorrect(oldPassword, userEmail);

        boolean isPasswordUpdated = false;
        if (isOldPasswordCorrect && !newPassword.equals("")) {
            user.setPasswordHash(PasswordHashing.hashPassword(newPassword));

            user.setFirstName(firstName);
            user.setSurname(surname);
            user.setPhoneNumber(phoneNumber);
            user.setCountry(country);
            user.setCity(city);
            user.setZipCode(zipCode);
            user.setFullAddress(address);
            userDao.update(user);
            isPasswordUpdated = true;
        }

        return isPasswordUpdated;

    }

}
