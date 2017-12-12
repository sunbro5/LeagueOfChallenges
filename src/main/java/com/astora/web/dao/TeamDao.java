package com.astora.web.dao;

import com.astora.web.dao.model.League;
import com.astora.web.dao.model.Team;

import java.util.List;

public interface TeamDao extends EntityDao<Team>{

    Long getTeamsCountByLeague(List<League> league);
}
