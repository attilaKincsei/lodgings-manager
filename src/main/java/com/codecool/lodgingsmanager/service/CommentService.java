package com.codecool.lodgingsmanager.service;

import com.codecool.lodgingsmanager.model.Comment;
import com.codecool.lodgingsmanager.model.Lodgings;
import com.codecool.lodgingsmanager.model.User;

import java.util.List;

public class CommentService implements BaseService<Comment> {
    @Override
    public User handleGetUserBy(String userEmail) {
        return null;
    }

    @Override
    public List<Comment> handleGetAllBy(long id) {
        // todo
        return null;
    }

    @Override
    public void handleDeletion(long id) {
        // todo
    }

    @Override
    public void injectDependency(BaseService handler) {
        // todo
    }

    @Override
    public void handleAddNew(Comment object) {
        // todo
    }

    @Override
    public void handleUpdate(Comment user) {
        // todo
    }
}
