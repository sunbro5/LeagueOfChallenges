package com.astora.web.service;

import com.astora.web.dao.model.User;
import com.astora.web.dto.FriendInfoDto;
import com.astora.web.dto.UserInfoDto;
import com.astora.web.dto.message.MessageDto;
import com.astora.web.dto.message.UserMessagesDto;
import com.astora.web.exception.ServiceException;
import com.astora.web.exception.UserDoesntExists;
import com.astora.web.model.SendMessageModel;

import java.util.List;

/**
 * @author <a href="mailto:maresjan694@gmail.com">Jan Mares</a>, 05.11.2017
 */
public interface UserService {

    void updateUser(User user);

    List<String> getUsersNicknameLike(String nickname);

    User getUserById(int userId) throws ServiceException;

    User getUserByNickname(String nickname) throws UserDoesntExists;

    UserInfoDto getUserInfoByNickname(String nickname) throws UserDoesntExists;
}
