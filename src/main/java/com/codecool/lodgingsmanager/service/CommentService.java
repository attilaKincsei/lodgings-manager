package com.codecool.lodgingsmanager.service;

import com.codecool.lodgingsmanager.model.Comment;
import com.codecool.lodgingsmanager.model.Lodgings;
import com.codecool.lodgingsmanager.model.User;

import java.util.List;

public class CommentService extends BaseService<Comment> {
    @Override
    public List<Comment> handleGetListBy(String stringParam, long longParam) {
        return null;
    }

    @Override
    public Comment handleGetSingleObjectBy(String param) {
        return null;
    }

    @Override
    public List<Comment> handleGetListBy(long id) {
        return null;
    }

    @Override
    public void handleDeletion(long id) {

    }

    @Override
    public void handleAddNew(Comment object) {

    }

    @Override
    public void handleUpdate(Comment user) {

    }
}
