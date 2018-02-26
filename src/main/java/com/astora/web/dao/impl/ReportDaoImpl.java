package com.astora.web.dao.impl;


import com.astora.web.dao.ReportDao;
import com.astora.web.dao.model.Report;
import com.astora.web.dao.model.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author <a href="mailto:maresjan694@gmail.cz">Jan Mares</a>, 16.11.2017
 */
@Repository("reportDao")
public class ReportDaoImpl extends EntityDaoImpl<Report> implements ReportDao {

    protected ReportDaoImpl() {
        super(Report.class);
    }

    public List<Report> getReportWithUsers(User user1, User user2){
        Session session =  sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Report WHERE userByReportingUserId = :user1Id AND " +
                "userByReportedUserId = :user2Id",Report.class);
        query.setParameter("user1Id", user1);
        query.setParameter("user2Id", user2);
        return query.getResultList();
    }
}
