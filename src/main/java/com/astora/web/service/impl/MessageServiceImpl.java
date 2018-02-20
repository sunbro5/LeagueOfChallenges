package com.astora.web.service.impl;

import com.astora.web.dao.MessageDao;
import com.astora.web.dao.model.Message;
import com.astora.web.dao.model.User;
import com.astora.web.dto.message.MessageDto;
import com.astora.web.dto.message.UserMessagesDto;
import com.astora.web.exception.CantSendMessageException;
import com.astora.web.exception.ServiceException;
import com.astora.web.exception.UserDoesntExists;
import com.astora.web.model.SendMessageModel;
import com.astora.web.service.MessageService;
import com.astora.web.service.UserService;
import com.astora.web.session.UserSessionManager;
import com.astora.web.utils.CustomValidationUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author <a href="mailto:maresjan694@gmail.com">Jan Mares</a>, 16.11.2017
 */
@Service("messageService")
public class MessageServiceImpl implements MessageService {

    private static final Logger logger = Logger.getLogger(MessageServiceImpl.class);

    private static final int PREVIEW_CHAR_COUNT = 20;
    private static final long SECOND = 1000;
    private static final int MESSAGE_INBOX_COUNT = 10;
    private static final int MESSAGE_NOTIFICATION_COUNT = 5;

    @Autowired
    private MessageDao messageDao;

    @Autowired
    private UserService userService;

    @Autowired
    private UserSessionManager userSessionManager;

    @Transactional
    public List<MessageDto> getUserMessagesWithUser(int userId, String friendNickname) throws ServiceException {
        List<MessageDto> messageDto = new ArrayList<MessageDto>();
        User user = userService.getUserById(userId);
        User userFriend = userService.getUserByNickname(friendNickname);

        List<Message> messageList = messageDao.getMessageWithUsers(user, userFriend);
        markAsRead(messageList);
        messageDto.addAll(mapMessagesDto(messageList, true));
        messageDto.addAll(mapMessagesDto(messageDao.getMessageWithUsers(userFriend, user), false));
        if (CustomValidationUtils.isEmpty(messageDto)) {
            return messageDto;
        }
        messageDto.sort((o1, o2) -> o1.getSentDate().compareTo(o2.getSentDate()));
        return messageDto;
    }
    @Transactional(readOnly = true)
    public List<UserMessagesDto> getNotificationMessagesPreview(int userId) throws ServiceException {
        return getNewestMessagesPreview(userId, MESSAGE_NOTIFICATION_COUNT, true);
    }

    @Transactional(readOnly = true)
    public List<UserMessagesDto> getNewestMessagesPreview(int userId) throws ServiceException {
        return getNewestMessagesPreview(userId, MESSAGE_INBOX_COUNT, false);
    }

    private List<UserMessagesDto> getNewestMessagesPreview(int userId, int rowsCount, boolean onlyNotRead) throws ServiceException {
        List<UserMessagesDto> messagesDto = new ArrayList<UserMessagesDto>();
        User user = userService.getUserById(userId);
        List<Integer> friendsId;
        if (onlyNotRead) {
            friendsId = messageDao.getNotReadNewestMessagesUserId(userId, rowsCount);
        } else {
            friendsId = messageDao.getNewestMessagesUserId(userId, rowsCount);
        }

        Message message;
        for (Integer friendId : friendsId) {
            User friend = userService.getUserById(friendId);
            message = messageDao.getNewestMessageWithUsers(user, friend);
            if (message != null) {
                UserMessagesDto userMessage = new UserMessagesDto(message.getUserByFromUserId().getNickname());
                String textPreview = getPreviewFromText(message.getText());
                userMessage.setAlreadyRead(message.getAlreadyRead());
                userMessage.setTextPreview(textPreview);
                userMessage.setTextPreviewDate(message.getCreated());
                messagesDto.add(userMessage);
            }
        }
        return messagesDto;
    }

    @Transactional
    public void sendMessage(int userId, SendMessageModel model) throws ServiceException {
        User user = userService.getUserById(userId);
        User userFriend = userService.getUserByNickname(model.getToNickname());
        validateMessage();
        Message message = new Message();
        message.setSubject(model.getSubject());
        message.setText(model.getText());
        message.setUserByFromUserId(user);
        message.setUserByToUserId(userFriend);
        messageDao.create(message);
    }

    private List<MessageDto> mapMessagesDto(List<Message> messages, boolean received) {
        List<MessageDto> messageDtos = new ArrayList<MessageDto>();
        for (Message message : messages) {
            messageDtos.add(new MessageDto(received, message.getText(), message.getCreated()));
        }
        return messageDtos;
    }

    private String getPreviewFromText(String text) {
        if (text != null && !text.equals("") && text.length() > PREVIEW_CHAR_COUNT) {
            text = text.substring(0, PREVIEW_CHAR_COUNT);
        }
        return text;
    }

    private void validateMessage() throws CantSendMessageException {
        Date now = new Date();
        Date lastMessage = userSessionManager.getLastMessageTime();
        if (lastMessage != null && (lastMessage.getTime() + SECOND) > now.getTime()) {
            throw new CantSendMessageException("Message time limit do not approve message send.");
        }
        userSessionManager.setLastMessageTime(now);
    }

    public void markAsRead(List<Message> messageList) {
        messageList.stream().filter(message -> message.getAlreadyRead() == 0).forEach(message -> {
            message.setAlreadyRead(1);
            messageDao.update(message);
        });
    }
}
