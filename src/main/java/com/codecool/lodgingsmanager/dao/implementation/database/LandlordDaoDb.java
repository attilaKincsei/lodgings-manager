package com.codecool.lodgingsmanager.dao.implementation.database;


import com.codecool.lodgingsmanager.model.Landlord;

public class LandlordDaoDb extends UserDaoDb<Landlord> {


    public LandlordDaoDb(Class<Landlord> classType) {
        super(classType);
    }
}
