package com.astora.web.dao.impl;

import com.astora.web.dao.ChallengeDao;
import com.astora.web.dao.model.Challenge;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository("challengeDao")
@Transactional
public class ChallengeDaoImpl extends EntityDaoImpl<Challenge> implements ChallengeDao {

    protected ChallengeDaoImpl() {
        super(Challenge.class);
    }
}
