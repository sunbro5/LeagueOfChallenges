package com.astora.web.service.impl;

import com.astora.web.cache.GameCache;
import com.astora.web.dao.TeamDao;
import com.astora.web.dao.model.*;
import com.astora.web.dto.games.GamesInfoDto;
import com.astora.web.dto.games.TeamInfoDto;
import com.astora.web.enums.Leagues;
import com.astora.web.exception.ServiceException;
import com.astora.web.service.GameService;
import com.astora.web.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("gameService")
@Transactional
public class GameServiceImpl implements GameService {

    private static final Logger logger = Logger.getLogger(GameServiceImpl.class);

    private UserService userService;

    private TeamDao teamDao;

    private GameCache gameCache;

    @Autowired
    public void setGameCache(GameCache gameCache) {
        this.gameCache = gameCache;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setTeamDao(TeamDao teamDao) {
        this.teamDao = teamDao;
    }

    public boolean noTeamGame(String gameName) throws ServiceException {
        return gameCache.getGame(gameName).noTeam();
    }

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


    public Leagues getLeagueForNoTeamGame(int userId, String gameName) throws ServiceException {
        User user = userService.getUserById(userId);
        Game game = gameCache.getGame(gameName);
        if(game.noTeam()){
            throw new ServiceException("Game is not NoTeam game: " + gameName);
        }

    }

    public List<TeamInfoDto> getTeamsByGameName(int userId, String gameName) throws ServiceException {
        User user = userService.getUserById(userId);
        Game game = gameCache.getGame(gameName);
        List<TeamInfoDto> teams = new ArrayList<TeamInfoDto>();
        for (TeamUser teamUser : user.getTeamUsersByUserId()) {
            Team team = teamUser.getTeamByTeamTeamId();
            if (team.getLeagueByLeagueLeaguId().getGameByGameGameId().getGameId() == game.getGameId()) {
                teams.add(mapTeamInfo(team));
            }
        }
        return teams;
    }

    private TeamInfoDto mapTeamInfo(Team team) {
        TeamInfoDto teamInfo = new TeamInfoDto(team);
        List<String> users = new ArrayList<String>();
        for (TeamUser teamUser : team.getTeamUsersByTeamId()) {
            users.add(teamUser.getUserByUserUserId().getNickname());
        }
        teamInfo.setUsers(users);
        teamInfo.setLeague(Leagues.valueOf(team.getLeagueByLeagueLeaguId().getLeagueName()));
        return teamInfo;
    }
}
