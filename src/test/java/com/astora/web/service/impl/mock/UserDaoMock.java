package com.astora.web.service.impl.mock;

import com.astora.web.dao.UserDao;
import com.astora.web.dao.model.User;

import java.util.Collection;
import java.util.List;

/**
 * @author <a href="mailto:maresjan694@gmail.com">Jan Mares</a>, 7.12.2017
 */
public class UserDaoMock implements UserDao {

    public User getUserByNickname(String nickname) {
        return null;
    }

    public List<User> getUserLikeNickname(String nickname) {
        return null;
    }

    public void create(User object) {

    }

    public void update(User object) {

    }

    public User findById(int id) {
        return null;
    }

    public List<User> findAll() {
        return null;
    }

    public void delete(int id) {

    }

    public User getByUniqueColumnValue(String columnName, Object value) {
        return null;
    }

    public List<User> getListLikeColumnValue(String columnName, String value) {
        return null;
    }

    public List<User> getListInColumnValue(String columnName, Collection collection) {
        return null;
    }
}
