package com.codecool.lodgingsmanager.dao.implementation.database;

import com.codecool.lodgingsmanager.dao.LandlordDao;

public class LandlordDaoDb extends UserDaoDb implements LandlordDao {


    private static LandlordDaoDb LANDLORD_INSTANCE = null;

    private LandlordDaoDb() {
    }

    public static LandlordDaoDb getINSTANCE() {
        if (LANDLORD_INSTANCE == null) {
            LANDLORD_INSTANCE = new LandlordDaoDb();
        }
        return LANDLORD_INSTANCE;
    }

}
