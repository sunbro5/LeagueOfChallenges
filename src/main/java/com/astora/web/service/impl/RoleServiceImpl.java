package com.astora.web.service.impl;

import com.astora.web.dao.RoleDao;
import com.astora.web.model.Role;
import com.astora.web.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleDao roleDao;

    @Autowired
    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public void create(Role role) {
        roleDao.create(role);
    }

    public void update(Role role) {
        roleDao.update(role);
    }

    public Role findById(int id) {
        return roleDao.findById(id);
    }

    public List<Role> findAll() {
        return roleDao.findAll();
    }

    public void delete(int id) {
        roleDao.delete(id);
    }
}
