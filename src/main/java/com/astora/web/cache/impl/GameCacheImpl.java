package com.astora.web.cache.impl;

import com.astora.web.cache.GameCache;
import com.astora.web.dao.GameDao;
import com.astora.web.dao.model.Game;
import com.astora.web.exception.ServiceException;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

/**
 * @author <a href="mailto:maresjan694@gmail.com">Jan Mares</a>, 10.12.2017
 */
@Component("gameCache")
public class GameCacheImpl implements GameCache {

    private GameDao gameDao;

    @Autowired
    public void setGameDao(GameDao gameDao) {
        this.gameDao = gameDao;
    }

    @Cacheable(value = "cacheGames")
    @Transactional(readOnly = true)
    public List<Game> getAllGames() {
        List<Game> gameList = gameDao.findAll();
        gameList.stream().map(Game::getLeaguesByGameId).forEach(Hibernate::initialize);
        gameList.stream().map(Game::getGameParamsByGameId).forEach(Hibernate::initialize);
        return gameList;
    }

    @Cacheable(value = "cacheGames")
    @Transactional(readOnly = true)
    public Game getGame(String gameName) throws ServiceException {
        Game game = gameDao.getByUniqueColumnValue("gameName", gameName);
        if (game == null) {
            throw new ServiceException("Game with name: " + gameName + " doesnt exists.");
        }
        Hibernate.initialize(game.getLeaguesByGameId());
        return game;
    }
}
