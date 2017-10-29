package com.astora.web.service;

import com.astora.web.model.Role;

import java.util.List;

public interface RoleService {

    void create(Role role);

    void update(Role role);

    Role findById(int id);

    List<Role> findAll();

    void delete(int id);
}
