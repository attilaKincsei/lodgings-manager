package com.codecool.lodgingsmanager.dao;

import com.codecool.lodgingsmanager.model.Lodgings;
import com.codecool.lodgingsmanager.model.ToDo;

import java.util.List;

public interface ToDoDao extends BaseDAO<ToDo> {
    List<ToDo> getAllTodosBy(List<Lodgings> lodgingsList);

    List<ToDo> getAllTodosBy(long lodgingsId);
}
