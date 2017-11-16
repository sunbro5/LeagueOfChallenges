package com.astora.web.service;

import com.astora.web.dto.FriendInfoDto;
import com.astora.web.exception.ServiceException;

import java.util.List;

/**
 * @author <a href="mailto:maresjan694@gmail.cz">Jan Mares</a>, 16.11.2017
 */
public interface FriendService {

    void createFriend(int userId, String friendNickname) throws ServiceException;

    boolean removeFriendByNickname(int userId, String friendNickname) throws ServiceException;

    List<FriendInfoDto> getFriendList(int userId) throws ServiceException;
}
