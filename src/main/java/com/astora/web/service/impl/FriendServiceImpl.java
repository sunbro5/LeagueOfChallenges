package com.astora.web.service.impl;

import com.astora.web.dao.FriendDao;
import com.astora.web.dao.model.Friend;
import com.astora.web.dao.model.User;
import com.astora.web.dto.FriendInfoDto;
import com.astora.web.exception.FriendAlreadyExistsException;
import com.astora.web.exception.ServiceException;
import com.astora.web.service.FriendService;
import com.astora.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:maresjan694@gmail.com">Jan Mares</a>, 16.11.2017
 */
@Service("friendService")
@Transactional
public class FriendServiceImpl implements FriendService {

    @Autowired
    private FriendDao friendDao;

    @Autowired
    private UserService userService;

    public boolean removeFriendByNickname(int userId, String friendNickname) throws ServiceException {
        User user = userService.getUserById(userId);
        List<FriendInfoDto> list = new ArrayList<FriendInfoDto>();
        for (Friend friend : user.getFriendsByUserId()) {
            if(friend.getUserByUserFriendId().getNickname().equals(friendNickname)){
                friendDao.delete(friend.getFriendId());
                return true;
            }
        }
        return false;
    }

    public void createFriend(int userId, String friendNickname) throws ServiceException {
        User user = userService.getUserById(userId);
        User userFriend = userService.getUserByNickname(friendNickname);
        if(user.equals(userFriend)){
            return;
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

    public List<FriendInfoDto> getFriendList(int userId) throws ServiceException {
        User user = userService.getUserById(userId);
        List<FriendInfoDto> list = new ArrayList<FriendInfoDto>();
        for (Friend friend : user.getFriendsByUserId()) {
            FriendInfoDto friendInfo = new FriendInfoDto();
            friendInfo.setNickname(friend.getUserByUserFriendId().getNickname());
            list.add(friendInfo);
        }
        return list;
    }
}
