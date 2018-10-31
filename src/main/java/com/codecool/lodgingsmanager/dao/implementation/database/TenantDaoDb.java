package com.codecool.lodgingsmanager.dao.implementation.database;

import com.codecool.lodgingsmanager.dao.TenantDao;

public class TenantDaoDb extends UserDaoDb implements TenantDao {

    private static TenantDao INSTANCE = null;

    private TenantDaoDb() {
    }

    public static TenantDao getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new TenantDaoDb();
        }
        return INSTANCE;
    }
}
