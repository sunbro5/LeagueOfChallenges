package com.astora.web.dao.impl;

import com.astora.web.dao.MessageDao;
import com.astora.web.dao.model.Message;
import com.astora.web.dao.model.User;
import com.astora.web.utils.CustomValidationUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
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

    @SuppressWarnings("unchecked")
    public List<Message> getMessageWithUsers(User user1, User user2){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Message WHERE userByToUserId = :toUser AND userByFromUserId = :fromUser");
        query.setParameter("toUser", user1);
        query.setParameter("fromUser", user2);
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Integer> getNewestMessagesUserId(int userId, int rowsCount){
        String sql = "SELECT from_user_id, max(created) as sentDate from message where to_user_id = :userId group by from_user_id order by sentDate desc";
        Query query = sessionFactory.getCurrentSession().createNativeQuery(sql);
        query.setParameter("userId",userId);
        query.setMaxResults(rowsCount);
        List<Integer> list = new ArrayList<Integer>();
        for (Iterator it = query.list().iterator(); it.hasNext();){
            Object[] result = (Object[]) it.next();
            list.add((Integer) result[0]);
        }
        return list;
    }

    @SuppressWarnings("unchecked")
    public List<Integer> getNotReadNewestMessagesUserId(int userId, int rowsCount){
        String sql = "SELECT from_user_id, max(created) as sentDate from message where to_user_id = :userId and already_read = 0 group by from_user_id order by sentDate desc ";
        Query query = sessionFactory.getCurrentSession().createNativeQuery(sql);
        query.setParameter("userId",userId);
        query.setMaxResults(rowsCount);
        List<Integer> list = new ArrayList<Integer>();
        for (Iterator it = query.list().iterator(); it.hasNext();){
            Object[] result = (Object[]) it.next();
            list.add((Integer) result[0]);
        }
        return list;
    }

    @SuppressWarnings("unchecked")
    public Message getNewestMessageWithUsers(User user1, User user2) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Message WHERE userByToUserId = :toUser AND userByFromUserId = :fromUser ORDER BY CREATED DESC");
        query.setParameter("toUser", user1);
        query.setParameter("fromUser", user2);
        query.setMaxResults(1);
        List<Message> list = query.getResultList();
        if (CustomValidationUtils.isEmpty(list)) {
            return null;
        }
        return list.get(0);
    }

}
