package com.astora.web.service.impl;

import com.astora.web.dao.TeamDao;
import com.astora.web.dao.model.Team;
import com.astora.web.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("teamService")
@Transactional
public class TeamServiceImpl implements TeamService {

    private TeamDao teamDao;

    @Autowired
    public void setTeamDao(TeamDao teamDao) {
        this.teamDao = teamDao;
    }

    public void create(Team team) {

    }

    public void update(Team tean) {

    }

    public Team findById(int id) {
        return teamDao.findById(id);
    }

    public List<Team> findAll() {
        return null;
    }

    public void delete(int id) {

    }


}
