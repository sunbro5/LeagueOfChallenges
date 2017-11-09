package com.astora.web.service.impl;

import com.astora.web.dao.FriendDao;
import com.astora.web.dao.MessageDao;
import com.astora.web.dao.UserDao;
import com.astora.web.dao.model.Friend;
import com.astora.web.dao.model.Message;
import com.astora.web.dao.model.User;
import com.astora.web.dto.FriendInfoDto;
import com.astora.web.dto.message.MessageDto;
import com.astora.web.dto.message.UserMessagesDto;
import com.astora.web.exception.FriendAlreadyExistsException;
import com.astora.web.exception.ServiceException;
import com.astora.web.exception.UserDoesntExists;
import com.astora.web.model.SendMessageModel;
import com.astora.web.service.UserService;
import com.astora.web.utils.CustomValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private FriendDao friendDao;

    @Autowired
    private MessageDao messageDao;

    public List<FriendInfoDto> getFriendList(int userId) {
        User user = userDao.findById(userId);
        List<FriendInfoDto> list = new ArrayList<FriendInfoDto>();
        for (Friend friend : user.getFriendsByUserId()) {
            FriendInfoDto friendInfo = new FriendInfoDto();
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

    public void createFriend(int userId, String friendNickname) throws ServiceException {
        User user = userDao.findById(userId);
        User userFriend = userDao.getUserByNickname(friendNickname);
        if (userFriend == null) {
            throw new UserDoesntExists("User with nickname" + friendNickname + "does not exists");
        }
        if (user == null) {
            throw new ServiceException("Cant load user with id: " + userId);
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

    public UserMessagesDto getUserMessagesWithUser(int userId, String friendNickname) throws ServiceException {
        User user = userDao.findById(userId);
        if (user == null) {
            throw new ServiceException("Cant load user with id: " + userId);
        }
        User userFriend = userDao.getUserByNickname(friendNickname);
        if (userFriend == null) {
            throw new UserDoesntExists("User with nickname" + friendNickname + "does not exists");
        }
        UserMessagesDto userMessagesDto = new UserMessagesDto(friendNickname);
        List<MessageDto> messageDto = new ArrayList<MessageDto>();
        messageDto.addAll(mapMessagesDto(messageDao.getMessageWithUsers(user.getUserId(), userFriend.getUserId()), true));
        messageDto.addAll(mapMessagesDto(messageDao.getMessageWithUsers(user.getUserId(), userFriend.getUserId()), false));
        if(CustomValidationUtils.isEmpty(messageDto)){
            return userMessagesDto;
        }
        Comparator<MessageDto> comparator = new Comparator<MessageDto>() {
            public int compare(MessageDto o1, MessageDto o2) {
                return o1.getSentDate().compareTo(o2.getSentDate());
            }
        };
        Collections.sort(messageDto, comparator);
        userMessagesDto.setMessages(messageDto);
        userMessagesDto.setTextPreview(messageDto.get(0).getText());
        return userMessagesDto;
    }


    public void sendMessage(int userId, SendMessageModel model) throws ServiceException {
        User user = userDao.findById(userId);
        if (user == null) {
            throw new ServiceException("Cant load user with id: " + userId);
        }
        User userFriend = userDao.getUserByNickname(model.getToNickname());
        if (userFriend == null) {
            throw new UserDoesntExists("User with nickname" + model.getToNickname() + "does not exists");
        }
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

}
