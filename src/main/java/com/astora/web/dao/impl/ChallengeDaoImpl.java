package com.astora.web.dao.impl;

import com.astora.web.dao.ChallengeDao;
import com.astora.web.dao.model.Challenge;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("challengeDao")
public class ChallengeDaoImpl extends EntityDaoImpl<Challenge> implements ChallengeDao {

    protected ChallengeDaoImpl() {
        super(Challenge.class);
    }
}
