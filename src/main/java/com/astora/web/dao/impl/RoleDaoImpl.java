package com.astora.web.dao.impl;

import com.astora.web.dao.EntityDao;
import com.astora.web.dao.RoleDao;
import com.astora.web.dao.model.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Repository("RoleDao")
public class RoleDaoImpl extends EntityDaoImpl<Role> implements RoleDao {

    public RoleDaoImpl(){
        super(Role.class);
    }

}
