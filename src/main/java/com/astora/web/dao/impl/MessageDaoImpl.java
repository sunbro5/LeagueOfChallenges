package com.astora.web.dao.impl;

import com.astora.web.dao.MessageDao;
import com.astora.web.dao.model.Message;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * @author <a href="mailto:maresjan694@gmail.com">Jan Mares</a>, 8.11.2017
 */
@Repository("messageDao")
@Transactional
public class MessageDaoImpl extends EntityDaoImpl<Message> implements MessageDao {

    public MessageDaoImpl() {
        super(Message.class);
    }
}
