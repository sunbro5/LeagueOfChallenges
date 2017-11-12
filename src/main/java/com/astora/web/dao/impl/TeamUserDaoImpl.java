package com.astora.web.dao.impl;

import com.astora.web.dao.TeamUserDao;
import com.astora.web.dao.model.Team;
import com.astora.web.dao.model.TeamUser;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository("teamUserDao")
@Transactional
public class TeamUserDaoImpl extends EntityDaoImpl<TeamUser> implements TeamUserDao {

    protected TeamUserDaoImpl() {
        super(TeamUser.class);
    }

    public List<Team> getAllTeamsForUser(int userId) {
        List<TeamUser> teamUsers = getListEqColumnValue("teamUserId", userId);
        List<Team> teams = new ArrayList<Team>();
        for (TeamUser teamUser : teamUsers) {
            teams.add(teamUser.getTeamByTeamTeamId());
        }
        return teams;
    }

}
