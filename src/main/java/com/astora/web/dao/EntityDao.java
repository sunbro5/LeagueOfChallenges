package com.astora.web.dao;

import java.util.Collection;
import java.util.List;

public interface EntityDao<T> {

    void create(T object);

    void update(T object);

    T findById(int id);

    List<T> findAll();

    void delete(int id);

    T getByUniqueColumnValue(String columnName, Object value);

    List<T> getListLikeColumnValue(String columnName, String value);

    List<T> getListInColumnValue(String columnName,Collection collection);
}
