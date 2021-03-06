package com.astora.web.dao.impl;

import com.astora.web.dao.ChallengeDao;
import com.astora.web.dao.model.Challenge;
import com.astora.web.dao.model.Team;
import com.astora.web.enums.ChallengeState;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;


import java.util.Date;
import java.util.List;

@Repository("challengeDao")
public class ChallengeDaoImpl extends EntityDaoImpl<Challenge> implements ChallengeDao {

    protected ChallengeDaoImpl() {
        super(Challenge.class);
    }

    @SuppressWarnings("unchecked")
    public List<Challenge> getActiveChallenges(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Challenge WHERE challengeStart > :start AND state = :allowedState");
        query.setParameter("start", new Date());
        query.setParameter("allowedState", ChallengeState.CREATED.name());
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Challenge> getChallengeForTeam(Team team){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Challenge WHERE teamByChallengerTeamId = :team");
        query.setParameter("team", team);
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Challenge> getChallengeOpponentTeam(Team team){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Challenge WHERE teamByOponnentTeamId = :team");
        query.setParameter("team", team);
        return query.getResultList();
    }
}
