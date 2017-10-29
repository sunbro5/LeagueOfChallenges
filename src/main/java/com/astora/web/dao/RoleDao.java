package com.astora.web.dao;

import com.astora.web.dao.model.Role;

import java.util.List;

public interface RoleDao {

    void create(Role role);

    void update(Role role);

    Role findById(int id);

    List<Role> findAll();

    void delete(int id);
}
