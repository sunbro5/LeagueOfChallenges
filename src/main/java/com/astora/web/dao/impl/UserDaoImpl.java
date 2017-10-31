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

@Repository("userDaoImpl")
public class UserDaoImpl extends EntityDaoImpl<User> implements UserDao{

    public UserDaoImpl(){
        super(User.class);
    }

    @Transactional
    public User getUserByUsername(String username){
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
        criteria.add(Restrictions.eq(User.COLUMN_NICKNAME,username));
        return (User) criteria.uniqueResult();
    }
}
