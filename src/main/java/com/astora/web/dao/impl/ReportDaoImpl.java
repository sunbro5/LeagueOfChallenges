package com.astora.web.dao.impl;


import com.astora.web.dao.ReportDao;
import com.astora.web.dao.model.Report;
import com.astora.web.dao.model.User;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
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
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Report.class);
        criteria.add(Restrictions.eq("userByReportingUserId", user1));
        criteria.add(Restrictions.eq("userByReportedUserId",user2));
        return criteria.list();
    }
}
