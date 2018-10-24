package com.codecool.lodgingsmanager.dao;

import com.codecool.lodgingsmanager.model.Lodgings;

import java.util.List;

public abstract class LodgingsDao extends BaseDAO<Lodgings> {

    public abstract List<Lodgings> getAllLodgingsBy(long userId);

}
