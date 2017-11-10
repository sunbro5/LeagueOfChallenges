package com.astora.web.dao.impl;

import com.astora.web.dao.MessageDao;
import com.astora.web.dao.model.Message;
import com.astora.web.dao.model.User;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
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

    public List<Message> getMessageWithUsers(User user1, User user2){
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Message.class);
        criteria.add(Restrictions.eq("userByToUserId",user1));
        criteria.add(Restrictions.eq("userByFromUserId",user2));
        return criteria.list();
    }

    public List<Integer> getNewestMessagesUserId(int userId){
        String sql = "SELECT from_user_id, max(created) as sentDate from message where to_user_id = :userId group by from_user_id order by sentDate desc LIMIT 10";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        query.setParameter("userId",userId);
        List<Integer> list = new ArrayList<Integer>();
        for (Iterator it = query.list().iterator(); it.hasNext();){
            Object[] result = (Object[]) it.next();
            list.add((Integer) result[0]);
        }
        return list;
    }

    public Message getNewestMessageWithUsers(User user1, User user2){
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Message.class);
        criteria.add(Restrictions.eq("userByToUserId",user1));
        criteria.add(Restrictions.eq("userByFromUserId",user2));
        criteria.addOrder(Order.desc("created"));
        criteria.setMaxResults(1);
        return (Message) criteria.uniqueResult();
    }

}
