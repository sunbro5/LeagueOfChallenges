package com.astora.web.dao.impl;

import com.astora.web.dao.EntityDao;
import com.astora.web.dao.RoleDao;
import com.astora.web.dao.model.Role;
import com.astora.web.enums.RoleType;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Repository("roleDao")
@Transactional
public class RoleDaoImpl extends EntityDaoImpl<Role> implements RoleDao {

    public RoleDaoImpl(){
        super(Role.class);
    }


    public Role getRoleByRoleType(RoleType type) {
        return getByUniqueColumnValue("name",type.name());
    }
}
