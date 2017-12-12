package com.astora.web.cache;

import com.astora.web.dao.model.Game;
import com.astora.web.exception.ServiceException;

import java.util.List;

/**
 * @author <a href="mailto:mares.jan@o2.cz">Jan Mares</a>, 10.12.2017
 */
public interface GameCache {

    List<Game> getAllGames();

    Game getGame(String gameName) throws ServiceException;
}
