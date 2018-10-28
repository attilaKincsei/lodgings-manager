package com.codecool.lodgingsmanager.dao;

import com.codecool.lodgingsmanager.model.Lodgings;

import java.util.List;

public abstract class LodgingsDao<T> extends BaseDAO<T> {

    public abstract List<T> getAllLodgingsBy(long userId);

}
