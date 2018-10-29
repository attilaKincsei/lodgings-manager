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
    public void handleAddNew(Lodgings newLodgings) {
        lodgingsDataManager.add(newLodgings);
    }

    @Override
    public Lodgings handleGetSingleObjectBy(String param) {
        return null; // todo
    }

    @Override
    public List<Lodgings> handleGetListBy(long userId) {
        return lodgingsDataManager.getAllLodgingsBy(userId);
    }

    @Override
    public List<Lodgings> handleGetListBy(String lodgingsId, long userId) {

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
    public void handleUpdate(Lodgings lodgings) {
        lodgingsDataManager.update(lodgings);
    }

    @Override
    public void handleDelete(long id) {
        lodgingsDataManager.remove(id);
    }


}
