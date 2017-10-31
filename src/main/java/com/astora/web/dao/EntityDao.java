package com.astora.web.dao;

import java.util.List;

public interface EntityDao<T> {

    void create(T role);

    void update(T role);

    T findById(int id);

    List<T> findAll();

    void delete(int id);
}
