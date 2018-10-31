package com.codecool.lodgingsmanager.dao.implementation.database;

import com.codecool.lodgingsmanager.dao.CommentDao;
import com.codecool.lodgingsmanager.dao.UserDao;
import com.codecool.lodgingsmanager.model.Comment;
import com.codecool.lodgingsmanager.model.User;

import java.util.List;

public class CommentDaoDb implements CommentDao {


    private static CommentDao INSTANCE = null;


    private CommentDaoDb() {
    }

    public static CommentDao getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new CommentDaoDb();
        }
        return INSTANCE;
    }



    @Override
    public Comment find(long id) {
        // todo
        return null;
    }

    @Override
    public void add(Comment object) {
        // todo
    }

    @Override
    public void update(Comment object) {
        // todo
    }

    @Override
    public void remove(long id) {
        // todo
    }

    @Override
    public List<Comment> getAll() {
        // todo
        return null;
    }
}
