package com.astora.web.service.impl;

import com.astora.web.cache.GameCache;
import com.astora.web.dao.TeamDao;
import com.astora.web.dao.TeamUserDao;
import com.astora.web.dao.model.*;
import com.astora.web.dto.games.GameTypeDto;
import com.astora.web.dto.games.GamesInfoDto;
import com.astora.web.dto.games.TeamInfoDto;
import com.astora.web.enums.Leagues;
import com.astora.web.exception.ServiceException;
import com.astora.web.service.GameService;
import com.astora.web.service.TeamService;
import com.astora.web.service.UserService;
import com.astora.web.utils.CustomValidationUtils;
import com.astora.web.utils.TeamUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("gameService")
public class GameServiceImpl implements GameService {

    private static final Logger logger = Logger.getLogger(GameServiceImpl.class);

    @Autowired
    private TeamDao teamDao;

    @Autowired
    private GameCache gameCache;

    @Autowired
    private TeamService teamService;

    @Transactional(readOnly = true)
    public boolean noTeamGame(String gameName) throws ServiceException {
        return gameCache.getGame(gameName).noTeam();
    }


    @Transactional(readOnly = true)
    public List<GamesInfoDto> getGamesInformation() {

        List<GamesInfoDto> gamesInfos = new ArrayList<GamesInfoDto>();
        for (Game game : gameCache.getAllGames()) {
            GamesInfoDto gameInfo = new GamesInfoDto(game);
            if (game.getLeaguesByGameId() == null) {
                logger.warn("Be aware !!! there is Game without LEAGUE !!!");
                continue;
            }
            long teamsCount = teamDao.getTeamsCountByLeague(new ArrayList<League>(game.getLeaguesByGameId()));
            gameInfo.setTeamsCount(teamsCount);
            gamesInfos.add(gameInfo);
        }
        return gamesInfos;
    }

    @Transactional(readOnly = true)
    public List<GameTypeDto> getAllGameNames() {
        List<GameTypeDto> gameTypes = new ArrayList<GameTypeDto>();
        for (Game game : gameCache.getAllGames()) {
            GameTypeDto gameType = new GameTypeDto();
            gameType.setGameName(game.getGameName());
            gameType.setNoTeamGame(game.noTeam());
            gameTypes.add(gameType);
        }
        return gameTypes;
    }

    @Transactional(readOnly = true)
    public Leagues getLeagueForNoTeamGame(int userId, String gameName) throws ServiceException {
        Game game = gameCache.getGame(gameName);
        if (!game.noTeam()) {
            throw new ServiceException("Game is not NoTeam game: " + gameName);
        }
        List<TeamInfoDto> team = teamService.getTeamsByGameName(userId, gameName);
        if (!CustomValidationUtils.isEmpty(team)) {
            TeamInfoDto teamInfoDto = team.get(0);
            return teamInfoDto.getLeague();
        }
        return null;
    }

    @Transactional(readOnly = true)
    public int getUsersGameCount(String gameName) throws ServiceException {
        return gameCache.getGame(gameName).getTeamMemberCount();

    }

}
