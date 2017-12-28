package com.astora.web.service;

import com.astora.web.dao.model.Game;
import com.astora.web.dto.games.GameTypeDto;
import com.astora.web.dto.games.GamesInfoDto;
import com.astora.web.dto.games.TeamInfoDto;
import com.astora.web.enums.Leagues;
import com.astora.web.exception.ServiceException;

import java.util.List;

public interface GameService {

    List<GamesInfoDto> getGamesInformation();

    boolean noTeamGame(String gameName) throws ServiceException;

    Leagues getLeagueForNoTeamGame(int userId, String gameName) throws ServiceException;

    List<GameTypeDto> getAllGameNames();

    int getUsersGameCount(String gameName);

}
