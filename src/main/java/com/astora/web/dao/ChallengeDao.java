package com.astora.web.dao;

import com.astora.web.dao.model.Challenge;

import java.util.List;

public interface ChallengeDao extends EntityDao<Challenge> {

    List<Challenge> getActiveChallenges();

}
