package com.astora.web.dao;

import com.astora.web.dao.model.Team;
import com.astora.web.dao.model.TeamUser;

import java.util.List;

public interface TeamUserDao extends EntityDao<TeamUser>{
    List<Team> getAllTeamsForUser(int userId);
}
