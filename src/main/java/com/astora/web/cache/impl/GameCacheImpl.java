package com.astora.web.cache.impl;

import com.astora.web.cache.GameCache;
import com.astora.web.dao.GameDao;
import com.astora.web.dao.model.Game;
import com.astora.web.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author <a href="mailto:mares.jan@o2.cz">Jan Mares</a>, 10.12.2017
 */
@Component("gameCache")
public class GameCacheImpl implements GameCache {

    private GameDao gameDao;

    @Autowired
    public void setGameDao(GameDao gameDao) {
        this.gameDao = gameDao;
    }

    @Cacheable(value = "cacheGames")
    public List<Game> getAllGames() {
        return gameDao.findAll();
    }

    @Cacheable(value = "cacheGames")
    public Game getGame(String gameName) throws ServiceException {
        Game game = gameDao.getByUniqueColumnValue("gameName",gameName);
        if(game == null){
            throw new ServiceException("Game with name: " + gameName + " doesnt exists.");
        }
        return game;
    }
}
