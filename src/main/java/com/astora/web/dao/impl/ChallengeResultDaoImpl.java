package com.astora.web.dao.impl;

import com.astora.web.dao.ChallengeResultDao;
import com.astora.web.dao.model.ChallengeResult;
import org.springframework.stereotype.Repository;

/**
 * @author <a href="mailto:mares.jan@o2.cz">Jan Mares</a>, 12.3.2018
 */
@Repository("challengeResultDao")
public class ChallengeResultDaoImpl extends EntityDaoImpl<ChallengeResult> implements ChallengeResultDao {

    protected ChallengeResultDaoImpl() {
        super(ChallengeResult.class);
    }
}
