package com.astora.web.dao.impl;

import com.astora.web.dao.UserDao;
import com.astora.web.dao.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("userDao")
@Transactional
public class UserDaoImpl extends EntityDaoImpl<User> implements UserDao{

    public UserDaoImpl(){
        super(User.class);
    }

    public User getUserByNickname(String nickname){
        return getByUniqueColumnValue("nickname",nickname);
    }

    public List<User> getUserLikeNickname(String nickname){
        return getListLikeColumnValue("nickname",nickname);
    }
}
