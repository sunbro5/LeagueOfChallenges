package com.astora.web.service;

import com.astora.web.dto.message.MessageDto;
import com.astora.web.dto.message.UserMessagesDto;
import com.astora.web.exception.ServiceException;
import com.astora.web.model.SendMessageModel;

import java.util.List;

/**
 * @author <a href="mailto:maresjan694@gmail.com">Jan Mares</a>, 16.11.2017
 */
public interface MessageService {

    List<UserMessagesDto> getNewestMessagesPreview(int userId) throws ServiceException;

    void sendMessage(int userId, SendMessageModel model) throws ServiceException;

    List<MessageDto> getUserMessagesWithUser(int userId, String friendNickname) throws ServiceException;

    List<UserMessagesDto> getNotificationMessagesPreview(int userId) throws ServiceException;
}
