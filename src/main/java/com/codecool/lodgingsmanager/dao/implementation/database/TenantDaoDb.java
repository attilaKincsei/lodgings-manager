package com.codecool.lodgingsmanager.dao.implementation.database;

import com.codecool.lodgingsmanager.model.Tenant;

public class TenantDaoDb extends UserDaoDb<Tenant> {

    public TenantDaoDb(Class<Tenant> classType) {
        super(classType);
    }
}
