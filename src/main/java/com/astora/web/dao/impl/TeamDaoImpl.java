package com.astora.web.dao.impl;

import com.astora.web.dao.TeamDao;
import com.astora.web.dao.model.Team;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("teamDao")
@Transactional
public class TeamDaoImpl extends EntityDaoImpl<Team> implements TeamDao {

    protected TeamDaoImpl() {
        super(Team.class);
    }
}
