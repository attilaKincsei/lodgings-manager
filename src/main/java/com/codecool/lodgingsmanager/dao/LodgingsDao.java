package com.codecool.lodgingsmanager.dao;

import com.codecool.lodgingsmanager.model.Lodgings;

import java.util.List;

public interface LodgingsDao extends BaseDAO<Lodgings> {

    List<Lodgings> getAllLodgingsBy(long userId);

}
