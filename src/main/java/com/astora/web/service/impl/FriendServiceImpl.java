package com.astora.web.service.impl;

import com.astora.web.dao.FriendDao;
import com.astora.web.dao.model.Friend;
import com.astora.web.dao.model.User;
import com.astora.web.dto.FriendInfoDto;
import com.astora.web.exception.FriendAlreadyExistsException;
import com.astora.web.exception.ServiceException;
import com.astora.web.service.FriendService;
import com.astora.web.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:maresjan694@gmail.com">Jan Mares</a>, 16.11.2017
 */
@Service("friendService")
public class FriendServiceImpl implements FriendService {

    private static final Logger logger = Logger.getLogger(FriendServiceImpl.class);

    @Autowired
    private FriendDao friendDao;

    @Autowired
    private UserService userService;

    @Transactional
    public boolean removeFriendByNickname(int userId, String friendNickname) {
        User user;
        try {
            user = userService.getUserById(userId);
        } catch (ServiceException e) {
            logger.error(e);
            return false;
        }
        for (Friend friend : user.getFriendsByUserId()) {
            if(friend.getUserByUserFriendId().getNickname().equals(friendNickname)){
                friendDao.delete(friend.getFriendId());
                return true;
            }
        }
        return false;
    }

    @Transactional
    public void createFriend(int userId, String friendNickname) throws ServiceException {
        User user = userService.getUserById(userId);
        User userFriend = userService.getUserByNickname(friendNickname);
        if(user.equals(userFriend)){
            throw new ServiceException("Cant add myself to friend list");
        }
        for (Friend friend : user.getFriendsByUserId()) {
            if (friend.getUserByUserFriendId().getNickname().equals(friendNickname)) {
                throw new FriendAlreadyExistsException("Friend with nickname:" + friendNickname + "already exists");
            }
        }
        Friend friend = new Friend();
        friend.setUserByUserId(user);
        friend.setUserByUserFriendId(userFriend);
        friendDao.create(friend);
    }

    @Transactional(readOnly = true)
    public List<FriendInfoDto> getFriendList(int userId) {
        List<FriendInfoDto> list = new ArrayList<FriendInfoDto>();
        User user;
        try {
            user = userService.getUserById(userId);
        } catch (ServiceException e) {
            logger.error(e);
            return list;
        }
        for (Friend friend : user.getFriendsByUserId()) {
            FriendInfoDto friendInfo = new FriendInfoDto();
            friendInfo.setNickname(friend.getUserByUserFriendId().getNickname());
            list.add(friendInfo);
        }
        return list;
    }
}
