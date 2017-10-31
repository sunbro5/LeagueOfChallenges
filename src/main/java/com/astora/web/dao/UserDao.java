package com.astora.web.dao;

import com.astora.web.dao.model.User;

public interface UserDao extends EntityDao<User> {

    User getUserByUsername(String username);
}
