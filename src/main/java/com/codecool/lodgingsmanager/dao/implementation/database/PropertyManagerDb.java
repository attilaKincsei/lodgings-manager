package com.codecool.lodgingsmanager.dao.implementation.database;

import com.codecool.lodgingsmanager.dao.PropertyManagerDao;

public class PropertyManagerDb extends UserDaoDb implements PropertyManagerDao {

    private static PropertyManagerDao INSTANCE = null;

    private PropertyManagerDb() {
    }

    public static PropertyManagerDao getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new PropertyManagerDb();
        }
        return INSTANCE;
    }
}
