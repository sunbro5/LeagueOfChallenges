package com.astora.web.dao.impl;

import com.astora.web.dao.GameDao;
import com.astora.web.dao.model.Game;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("gameDao")
@Transactional
public class GameDaoImpl extends EntityDaoImpl<Game> implements GameDao {

    protected GameDaoImpl() {
        super(Game.class);
    }
}
