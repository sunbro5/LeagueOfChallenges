package com.astora.web.service.impl;

import com.astora.web.dao.FriendDao;
import com.astora.web.dao.UserDao;
import com.astora.web.dao.model.Friend;
import com.astora.web.dao.model.User;
import com.astora.web.dto.FriendInfo;
import com.astora.web.exception.ServiceException;
import com.astora.web.exception.UserDoesntExists;
import com.astora.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private FriendDao friendDao;

    public List<FriendInfo> getFriendList(int userId) {
        User user = userDao.findById(userId);
        List<FriendInfo> list = new ArrayList<FriendInfo>();
        for (Friend friend : user.getFriendsByUserId()) {
            FriendInfo friendInfo = new FriendInfo();
            friendInfo.setNickname(friend.getUserByUserFriendId().getNickname());
            list.add(friendInfo);
        }
        return list;
    }

    public List<String> getUsersNicknameLike(String nickname) {
        List<User> users = userDao.getUserLikeNickname(nickname);
        List<String> nicknameList = new ArrayList<String>();
        for (User user : users) {
            nicknameList.add(user.getNickname());
        }
        return nicknameList;
    }

    public boolean createFriend(int userId, String friendNickname) throws ServiceException {
        User user = userDao.findById(userId);
        User userFriend = userDao.getUserByNickname(friendNickname);
        if (userFriend == null) {
            return false;
        }
        if (user == null) {
            throw new ServiceException("Cant load user with id: " + userId);
        }
        Friend friend = new Friend();
        friend.setUserByUserId(user);
        friend.setUserByUserFriendId(userFriend);
        friendDao.create(friend);
        return true;
    }


}
