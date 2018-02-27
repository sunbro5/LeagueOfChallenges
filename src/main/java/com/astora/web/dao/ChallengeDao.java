package com.astora.web.dao;

import com.astora.web.dao.model.Challenge;
import com.astora.web.dao.model.Team;

import java.util.List;

public interface ChallengeDao extends EntityDao<Challenge> {

    List<Challenge> getActiveChallenges();

    List<Challenge> getChallengeForTeam(Team team);

}
