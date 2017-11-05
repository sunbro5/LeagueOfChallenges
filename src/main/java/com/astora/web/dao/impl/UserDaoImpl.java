package com.astora.web.dao.impl;

import com.astora.web.dao.EntityDao;
import com.astora.web.dao.UserDao;
import com.astora.web.dao.model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("userDao")
@Transactional
public class UserDaoImpl extends EntityDaoImpl<User> implements UserDao{

    public UserDaoImpl(){
        super(User.class);
    }

    public User getUserByUsername(String username){
        return getByUniqueColumnValue("nickname",username);
    }
}
