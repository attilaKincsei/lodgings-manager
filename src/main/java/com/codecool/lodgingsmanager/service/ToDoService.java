package com.codecool.lodgingsmanager.service;

import com.codecool.lodgingsmanager.dao.ToDoDao;
import com.codecool.lodgingsmanager.model.Lodgings;
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
    public void handleDeletion(long id) {

    }

    @Override
    public String handleCrudGetBy(String requestPath, String id) {
        String templateToRender;
        switch (requestPath) {
            case "/todo":
                templateToRender = "todos.html";
                break;
            case "/todo/add":
                templateToRender = "add_todo.html";
                break;
            case "/todo/edit":
                templateToRender = "edit_todo.html";
                break;
            case "/todo/delete":
                templateToRender = null;
                break;
            default:
                templateToRender = "todos.html";
                break;
        }
        return templateToRender;
    }

    @Override
    public List<String> getEnumAsStringList() {
        return null;
    }

    @Override
    public boolean handleAddAndEditPost(String param1, String param2, String param3, String param4, String param5, String param6, String param7, String param8, String param9, String param10, String param11, String param12, String param13, String param14) {
        return false;
    }


    public List<ToDo> handleGetAllTodosBy(List<Lodgings> lodgingsList) {
        return toDoDao.getAllTodosBy(lodgingsList);
    }
}
