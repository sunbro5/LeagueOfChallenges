package com.astora.web.dao;

import com.astora.web.dao.model.Message;
import com.astora.web.dao.model.User;

import java.util.Collection;
import java.util.List;

/**
 * @author <a href="mailto:maresjan694@gmail.com">Jan Mares</a>, 8.11.2017
 */
public interface MessageDao extends EntityDao<Message> {

    List<Message> getMessageWithUsers(User user1, User user2);

    List<Integer> getNewestMessagesUserId(int userId);

    Message getNewestMessageWithUsers(User user1, User user2);

}
