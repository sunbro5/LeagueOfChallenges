package com.astora.web.dao.impl;

import com.astora.web.dao.TeamUserDao;
import com.astora.web.dao.model.Team;
import com.astora.web.dao.model.TeamUser;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository("teamUserDao")
public class TeamUserDaoImpl extends EntityDaoImpl<TeamUser> implements TeamUserDao {

    public TeamUserDaoImpl() {
        super(TeamUser.class);
    }



}
