package com.codecool.lodgingsmanager.service;

import com.codecool.lodgingsmanager.dao.LodgingsDao;
import com.codecool.lodgingsmanager.dao.implementation.database.LodgingsDaoDb;
import com.codecool.lodgingsmanager.model.Lodgings;
import com.codecool.lodgingsmanager.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LodgingsService extends BaseService<Lodgings> {

    private final LodgingsDao<Lodgings> lodgingsDataManager;

    public LodgingsService(LodgingsDao<Lodgings> lodgingsDataManager) {
        this.lodgingsDataManager = lodgingsDataManager;
    }

    @Override
    public List<Lodgings> handleBy(String lodgingsId, long userId) {

        List<Lodgings> lodgingsList = new ArrayList<>();

        List<Lodgings> allLodgingsList = lodgingsDataManager.getAllLodgingsBy(userId);

        List<Long> lodgingsIdList = allLodgingsList.stream().mapToLong(Lodgings::getId).boxed().collect(Collectors.toList());

        if (lodgingsId != null && lodgingsIdList.contains(Long.parseLong(lodgingsId))) {
            Lodgings lodgings = lodgingsDataManager.find(Long.parseLong(lodgingsId));
            lodgingsList.add(lodgings);
        } else {
            lodgingsList = allLodgingsList;
        }
        return lodgingsList;
    }

    @Override
    public Lodgings handleBy(String userEmail) {
        return null;
    }

    @Override
    public void delete(long id) {
        lodgingsDataManager.remove(id);
    }

    @Override
    public void handleAddNewLodgings(Lodgings newLodgings) {
        lodgingsDataManager.add(newLodgings);
    }

    @Override
    public List<Lodgings> handleGettingLodgingsBy(User user) {
        return lodgingsDataManager.getAllLodgingsBy(user.getId());
    }

    @Override
    public void handleAddNewUser(User newUser) {

    }

    @Override
    public void handleUpdate(Lodgings user) {

    }


}
