package com.astora.web.service.impl;

import com.astora.web.dao.ChallengeDao;
import com.astora.web.dao.model.Challenge;
import com.astora.web.service.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("challengeService")
@Transactional
public class ChallengeServiceImpl implements ChallengeService {

    private ChallengeDao challengeDao;

    @Autowired
    public void setRoleDao(ChallengeDao challengeDao) {
        this.challengeDao = challengeDao;
    }

    public void create(Challenge challenge) {
        challengeDao.create(challenge);
    }

    public void update(Challenge challenge) {
        challengeDao.update(challenge);
    }

    public Challenge findById(int id) {
        return challengeDao.findById(id);
    }

    public List<Challenge> findAll() {
        return challengeDao.findAll();
    }

    public void delete(int id) {
        challengeDao.delete(id);
    }
}
