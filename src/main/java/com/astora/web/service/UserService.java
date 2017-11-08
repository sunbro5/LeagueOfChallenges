package com.astora.web.service;

import com.astora.web.dto.FriendInfo;
import com.astora.web.exception.ServiceException;

import java.util.List;

/**
 * @author <a href="mailto:maresjan694@gmail.com">Jan Mares</a>, 05.11.2017
 */
public interface UserService {

    List<FriendInfo> getFriendList(int userId);

    List<String> getUsersNicknameLike(String nickname);

    void createFriend(int userId, String friendNickname) throws ServiceException;
}
