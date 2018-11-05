package com.codecool.lodgingsmanager.service;

import com.codecool.lodgingsmanager.dao.LodgingsDao;
import com.codecool.lodgingsmanager.dao.implementation.database.LodgingsDaoDb;
import com.codecool.lodgingsmanager.model.Lodgings;
import com.codecool.lodgingsmanager.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LodgingsService implements BaseService<Lodgings> {

    private final LodgingsDao lodgingsDao = LodgingsDaoDb.getINSTANCE();
    private BaseService<User> userHandler;

    public LodgingsService(BaseService<User> userHandler) {
        this.userHandler = userHandler;
    }

    @Override
    public void handleAddNew(Lodgings newLodgings) {
        lodgingsDao.add(newLodgings);
    }


    @Override
    public User handleGetUserBy(String userEmail) {
        return userHandler.handleGetUserBy(userEmail);
    }

    @Override
    public List<Lodgings> handleGetAllBy(long userId) {
        return lodgingsDao.getAllLodgingsBy(userId);
    }

    @Override
    public void handleUpdate(Lodgings lodgings) {
        lodgingsDao.update(lodgings);
    }

    @Override
    public void handleDeletion(long id) {
        lodgingsDao.remove(id);
    }

    public List<Lodgings> handleGetAllLodgingsBy(long userId) {
        return lodgingsDao.getAllLodgingsBy(userId);
    }

    public List<Lodgings> handleGetLodgingsBy(String lodgingsId, long userId) {

        List<Lodgings> lodgingsList = new ArrayList<>();

        List<Lodgings> allLodgingsList = lodgingsDao.getAllLodgingsBy(userId);

        List<Long> lodgingsIdList = allLodgingsList.stream().mapToLong(Lodgings::getId).boxed().collect(Collectors.toList());

        if (lodgingsId != null && lodgingsIdList.contains(Long.parseLong(lodgingsId))) {
            Lodgings lodgings = lodgingsDao.find(Long.parseLong(lodgingsId));
            lodgingsList.add(lodgings);
        } else {
            lodgingsList = allLodgingsList;
        }
        return lodgingsList;
    }

    @Override
    public String handleCRUDBy(String requestPath, String lodgingsId) {
        String templateToRender;
        switch (requestPath) {
            case "/lodgings":
                templateToRender = "lodgings.html";
                break;
            case "/lodgings/add":
                templateToRender = "add_lodgings.html";
                break;
            case "/lodgings/edit":
                templateToRender = "edit_lodgings.html";
                break;
            case "/lodgings/delete":
                handleDeletion(Long.parseLong(lodgingsId));
                templateToRender = null;
                break;
            default:
                templateToRender = "lodgings.html";
                break;
        }
        return templateToRender;
    }
}
