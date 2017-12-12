package com.astora.web.dao.impl;

import com.astora.web.dao.TeamDao;
import com.astora.web.dao.model.League;
import com.astora.web.dao.model.Team;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("teamDao")
@Transactional
public class TeamDaoImpl extends EntityDaoImpl<Team> implements TeamDao {

    protected TeamDaoImpl() {
        super(Team.class);
    }

    public Long getTeamsCountByLeague(List<League> leagues){
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Team.class);
        criteria.add(Restrictions.in("leagueByLeagueLeaguId", leagues));
        criteria.setProjection(Projections.rowCount());
        return (Long) criteria.uniqueResult();
    }


}
