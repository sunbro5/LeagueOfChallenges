package com.astora.web.dao.impl;

import com.astora.web.dao.MessageDao;
import com.astora.web.dao.model.Message;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author <a href="mailto:maresjan694@gmail.com">Jan Mares</a>, 8.11.2017
 */
@Repository("messageDao")
@Transactional
public class MessageDaoImpl extends EntityDaoImpl<Message> implements MessageDao {

    public MessageDaoImpl() {
        super(Message.class);
    }

    public List<Message> getMessageWithUsers(int userId1, int userId2){
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Message.class);
        criteria.add(Restrictions.eq("userByToUserId",userId1));
        criteria.add(Restrictions.eq("userByFromUserId",userId2));
        return criteria.list();
    }

}
