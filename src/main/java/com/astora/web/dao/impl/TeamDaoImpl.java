package com.astora.web.dao.impl;

import com.astora.web.dao.TeamDao;
import com.astora.web.dao.model.League;
import com.astora.web.dao.model.Team;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("teamDao")
public class TeamDaoImpl extends EntityDaoImpl<Team> implements TeamDao {

    protected TeamDaoImpl() {
        super(Team.class);
    }

    public Long getTeamsCountByLeague(List<League> leagues){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT COUNT(*) FROM Team WHERE leagueByLeagueLeaguId IN (:list)");
        query.setParameter("list", leagues);
        return (Long) query.getSingleResult();
    }


}
