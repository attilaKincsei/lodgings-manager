package com.codecool.lodgingsmanager.service;

import com.codecool.lodgingsmanager.dao.LodgingsDao;
import com.codecool.lodgingsmanager.model.Lodgings;
import com.codecool.lodgingsmanager.model.User;
import com.codecool.lodgingsmanager.util.LodgingsType;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LodgingsService implements BaseService<Lodgings> {

    private final LodgingsDao lodgingsDao;
    private final BaseService<User> userHandler;

    public LodgingsService(LodgingsDao lodgingsDao, BaseService<User> userHandler) {
        this.lodgingsDao = lodgingsDao;
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
    public String handleCrudGetBy(String requestPath, String lodgingsId) {
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

    @Override
    public List<String> getEnumAsStringList() {
        return Arrays.stream(LodgingsType.values()).map(LodgingsType::getLodgingsTypeString).collect(Collectors.toList());
    }

    @Override
    public boolean handleAddAndEditPost(
            String lodgingName, String lodgingType, String country, String city, String zipCode, String address,
            String dailyPrice, String electricityBill, String gasBill, String telecommunicationBill, String cleaningCost,
            String landlordEmail, String requestPath, String lodgingsIdString,
            String propertyManagerEmail) {

        boolean isSuccessful = false;

        User user = handleGetUserBy(landlordEmail);

        if (requestPath.equals("/lodgings/add")) {


            Lodgings newLodgings = new Lodgings(
                    lodgingName,
                    LodgingsType.valueOf(lodgingType.toUpperCase()),
                    country,
                    city,
                    zipCode,
                    address,
                    Long.parseLong(dailyPrice),
                    Long.parseLong(electricityBill),
                    Long.parseLong(gasBill),
                    Long.parseLong(telecommunicationBill),
                    Long.parseLong(cleaningCost),
                    user
            );

            handleAddNew(newLodgings);

            isSuccessful = true;

        } else if (requestPath.equals("/lodgings/edit")) {

            Lodgings lodgings = handleGetLodgingsBy(lodgingsIdString, user.getId()).get(0);

            lodgings.setName(lodgingName);
            lodgings.setLodgingsType(LodgingsType.valueOf(lodgingType.toUpperCase()));
            lodgings.setCountry(country);
            lodgings.setCity(city);
            lodgings.setZipCode(zipCode);
            lodgings.setAddress(address);
            lodgings.setPricePerDay(Long.parseLong(dailyPrice));
            lodgings.setElectricityBill(Long.parseLong(electricityBill));
            lodgings.setGasBill(Long.parseLong(gasBill));
            lodgings.setTelecommunicationBill(Long.parseLong(telecommunicationBill));
            lodgings.setCleaningCost(Long.parseLong(cleaningCost));

            if (!propertyManagerEmail.equals("")) {
                try {
                    User mightBePropertyManager = userHandler.handleGetUserBy(propertyManagerEmail);
                    lodgings.setPropertyManager(mightBePropertyManager);
                } catch (NoResultException nre) {
                    // todo: logging
                    System.out.println("User email is not in the database, but NP");
                }
            }

            lodgingsDao.update(lodgings);
            isSuccessful = true;
        }

        return isSuccessful;
    }


}
