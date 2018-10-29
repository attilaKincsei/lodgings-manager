package com.codecool.lodgingsmanager.service;

import java.util.List;

public abstract class BaseService <E> {

    public abstract void handleAddNew(E object);

    public abstract E handleGetSingleObjectBy(String param);
    public abstract List<E> handleGetListBy(long id);
    public abstract List<E> handleGetListBy(String stringParam, long id);

    public abstract void handleUpdate(E object);

    public abstract void handleDelete(long id);
}
