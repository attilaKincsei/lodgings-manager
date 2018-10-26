package com.codecool.lodgingsmanager.service;

import com.codecool.lodgingsmanager.dao.LodgingsDao;
import com.codecool.lodgingsmanager.dao.implementation.database.LodgingsDaoDb;
import com.codecool.lodgingsmanager.model.Lodgings;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LodgingsService extends BaseService<Lodgings> {

    private LodgingsDao lodgingsDataManager = new LodgingsDaoDb();

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
}
