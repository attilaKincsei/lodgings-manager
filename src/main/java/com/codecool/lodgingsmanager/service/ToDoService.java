package com.codecool.lodgingsmanager.service;

import com.codecool.lodgingsmanager.dao.ToDoDao;
import com.codecool.lodgingsmanager.model.ToDo;
import com.codecool.lodgingsmanager.model.User;

import java.util.List;

public class ToDoService implements BaseService<ToDo> {

    private final ToDoDao toDoDao;
    private final BaseService<User> userHandler;

    public ToDoService(ToDoDao toDoDao, BaseService<User> userHandler) {
        this.toDoDao = toDoDao;
        this.userHandler = userHandler;
    }

    @Override
    public void handleAddNew(ToDo toDo) {
        this.toDoDao.add(toDo);
    }

    @Override
    public User handleGetUserBy(String userEmail) {
        return userHandler.handleGetUserBy(userEmail);
    }

    @Override
    public List handleGetAllBy(long id) {
        return null;
    }

    @Override
    public void handleUpdate(ToDo object) {

    }

    @Override
    public void handleDeletion(long id) {

    }

    @Override
    public String handleCRUDBy(String requestPath, String id) {
        return null;
    }

    @Override
    public List<String> getEnumAsStringList() {
        return null;
    }

    @Override
    public void handleAddOrEditWithPostRequest(String lodgingName, String lodgingType, String country, String city, String zipCode, String address, String dailyPrice, String electricityBill, String gasBill, String telecommunicationBill, String cleaningCost, String userEmail, String requestPath, String lodgingsIdString) {

    }
}
