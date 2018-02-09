package com.astora.web.dao.impl;

import com.astora.web.dao.ChallengeDao;
import com.astora.web.dao.model.Challenge;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;


import java.util.Date;
import java.util.List;

@Repository("challengeDao")
public class ChallengeDaoImpl extends EntityDaoImpl<Challenge> implements ChallengeDao {

    protected ChallengeDaoImpl() {
        super(Challenge.class);
    }

    public List<Challenge> getActiveChallenges(){
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Challenge.class);
        criteria.add(Restrictions.ge("challengeStart", new Date()));
        return criteria.list();
    }
}
