package com.astora.web.service.impl;

import com.astora.web.dao.MessageDao;
import com.astora.web.dao.model.Message;
import com.astora.web.dao.model.User;
import com.astora.web.dto.message.MessageDto;
import com.astora.web.dto.message.UserMessagesDto;
import com.astora.web.exception.ServiceException;
import com.astora.web.model.SendMessageModel;
import com.astora.web.service.MessageService;
import com.astora.web.service.UserService;
import com.astora.web.utils.CustomValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author <a href="mailto:maresjan694@gmail.com">Jan Mares</a>, 16.11.2017
 */
@Service("messageService")
@Transactional
public class MessageServiceImpl implements MessageService {

    private static final int PREVIEW_CHAR_COUNT = 20;

    @Autowired
    private MessageDao messageDao;

    @Autowired
    private UserService userService;

    public List<MessageDto> getUserMessagesWithUser(int userId, String friendNickname) throws ServiceException {
        User user = userService.getUserById(userId);
        User userFriend = userService.getUserByNickname(friendNickname);
        List<MessageDto> messageDto = new ArrayList<MessageDto>();
        messageDto.addAll(mapMessagesDto(messageDao.getMessageWithUsers(user, userFriend), true));
        messageDto.addAll(mapMessagesDto(messageDao.getMessageWithUsers(userFriend, user), false));
        if(CustomValidationUtils.isEmpty(messageDto)){
            return messageDto;
        }
        Comparator<MessageDto> comparator = new Comparator<MessageDto>() {
            public int compare(MessageDto o1, MessageDto o2) {
                return o1.getSentDate().compareTo(o2.getSentDate());
            }
        };
        return messageDto;
    }

    public List<UserMessagesDto> getNewestMessagesPreview(int userId) throws ServiceException {
        User user = userService.getUserById(userId);
        List<Integer> friendsId = messageDao.getNewestMessagesUserId(userId);
        Message message;
        List<UserMessagesDto> messagesDto = new ArrayList<UserMessagesDto>();
        for (Integer friendId: friendsId){
            User friend = userService.getUserById(friendId);
            message = messageDao.getNewestMessageWithUsers(user, friend);
            if(message != null){
                UserMessagesDto userMessage = new UserMessagesDto(message.getUserByFromUserId().getNickname());
                String textPreview = getPreviewFromText(message.getText());
                userMessage.setTextPreview(textPreview);
                userMessage.setTextPreviewDate(message.getCreated());
                messagesDto.add(userMessage);
            }
        }
        return messagesDto;
    }

    public void sendMessage(int userId, SendMessageModel model) throws ServiceException {
        User user = userService.getUserById(userId);
        User userFriend = userService.getUserByNickname(model.getToNickname());
        Message message = new Message();
        message.setSubject(model.getSubject());
        message.setText(model.getText());
        message.setUserByFromUserId(user);
        message.setUserByToUserId(userFriend);
        //TODO add tome cache for antispam ??? maybe 5 message per 10 min
        messageDao.create(message);
    }

    private List<MessageDto> mapMessagesDto(List<Message> messages, boolean received) {
        List<MessageDto> messageDtos = new ArrayList<MessageDto>();
        for (Message message : messages) {
            messageDtos.add(new MessageDto(received, message.getText(), message.getCreated()));
        }
        return messageDtos;
    }

    private String getPreviewFromText(String text){
        if(text != null && !text.equals("") && text.length() > PREVIEW_CHAR_COUNT){
            text = text.substring(0,PREVIEW_CHAR_COUNT);
        }
        return text;
    }
}
