package com.astora.web.dao;

import com.astora.web.dao.model.User;

import java.util.List;

public interface UserDao extends EntityDao<User> {

    User getUserByNickname(String nickname);

    List<User> getUserLikeNickname(String nickname);
}
