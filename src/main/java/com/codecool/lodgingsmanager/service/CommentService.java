package com.codecool.lodgingsmanager.service;

import com.codecool.lodgingsmanager.model.Comment;
import com.codecool.lodgingsmanager.model.Lodgings;
import com.codecool.lodgingsmanager.model.User;

import java.util.List;

public class CommentService extends BaseService<Comment> {
    @Override
    public List<Comment> handleGetListBy(String stringParam, long longParam) {
        // todo
        return null;
    }

    @Override
    public User handleGetUserBy(String userEmail) {
        return null;
    }

    @Override
    public List<Comment> handleGetListBy(long id) {
        // todo
        return null;
    }

    @Override
    public void handleDeletion(long id) {
        // todo
    }

    @Override
    public List<Lodgings> handleGetAllLodgingsBy(long userId) {
        return null;
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
