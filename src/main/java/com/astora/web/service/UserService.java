package com.astora.web.service;

import com.astora.web.dto.FriendInfoDto;
import com.astora.web.dto.message.UserMessagesDto;
import com.astora.web.exception.ServiceException;
import com.astora.web.model.SendMessageModel;

import java.util.List;

/**
 * @author <a href="mailto:maresjan694@gmail.com">Jan Mares</a>, 05.11.2017
 */
public interface UserService {

    List<FriendInfoDto> getFriendList(int userId);

    List<String> getUsersNicknameLike(String nickname);

    void createFriend(int userId, String friendNickname) throws ServiceException;

    UserMessagesDto getUserMessagesWithUser(int userId, String friendNickname) throws ServiceException;

    void sendMessage(int userId, SendMessageModel model) throws ServiceException;
}
