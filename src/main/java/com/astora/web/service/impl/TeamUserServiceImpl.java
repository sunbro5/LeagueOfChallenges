package com.astora.web.service.impl;

import com.astora.web.dao.TeamUserDao;
import com.astora.web.dao.model.Team;
import com.astora.web.service.TeamUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("teamUserService")
@Transactional
public class TeamUserServiceImpl implements TeamUserService {

    private TeamUserDao teamUserDao;

    @Autowired
    public void setTeamUserDao(TeamUserDao teamUserDao) {
        this.teamUserDao = teamUserDao;
    }

    public List<Team> getAllTeamsForUser(int userId){
        return  teamUserDao.getAllTeamsForUser(userId);
    }
}
