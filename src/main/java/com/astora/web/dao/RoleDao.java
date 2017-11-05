package com.astora.web.dao;

import com.astora.web.dao.model.Role;
import com.astora.web.enums.RoleType;

import java.util.List;

public interface RoleDao extends EntityDao<Role> {
    Role getRoleByRoleType(RoleType type);
}
