package com.astora.web.dao;

import com.astora.web.dao.model.Message;

import java.util.List;

/**
 * @author <a href="mailto:maresjan694@gmail.com">Jan Mares</a>, 8.11.2017
 */
public interface MessageDao extends EntityDao<Message> {

    List<Message> getMessageWithUsers(int userId1, int userId2);
}
