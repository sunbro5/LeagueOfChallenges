package com.astora.web.service.impl;

import com.astora.web.cache.GameCache;
import com.astora.web.dao.TeamDao;
import com.astora.web.dao.TeamUserDao;
import com.astora.web.dao.model.*;
import com.astora.web.dto.games.TeamInfoDto;
import com.astora.web.dto.games.TeamPickDto;
import com.astora.web.enums.Leagues;
import com.astora.web.exception.ServiceException;
import com.astora.web.exception.UserDoesntExists;
import com.astora.web.model.NewTeamModel;
import com.astora.web.service.TeamService;
import com.astora.web.service.UserService;
import com.astora.web.utils.TeamUtils;
import javafx.util.Pair;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("teamService")
public class TeamServiceImpl implements TeamService {

    private static final Logger logger = Logger.getLogger(TeamServiceImpl.class);

    private UserService userService;
    private TeamDao teamDao;
    private GameCache gameCache;
    private TeamUserDao teamUserDao;

    @Autowired
    public void setTeamUserDao(TeamUserDao teamUserDao) {
        this.teamUserDao = teamUserDao;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setGameCache(GameCache gameCache) {
        this.gameCache = gameCache;
    }

    @Autowired
    public void setTeamDao(TeamDao teamDao) {
        this.teamDao = teamDao;
    }

    @Transactional
    public void createDefaultTeam(int userId, String gameName)  {
        User user;
        League defaultLeague;
        Game game;
        try {
            user = userService.getUserById(userId);
            game = gameCache.getGame(gameName);
            defaultLeague = getLeagueFromGame(game, Leagues.getDefault());
        } catch (ServiceException e) {
            logger.error(e);
            return;
        }
        List<TeamUser> teamUserList = new ArrayList<TeamUser>();
        Team team = new Team();
        team.setName(TeamUtils.getDefaultTeamName(user, game));
        team.setDescription("default");
        team.setRating(0);
        team.setLeagueByLeagueLeaguId(defaultLeague);
        TeamUser teamUser = new TeamUser();
        teamUser.setUserByUserUserId(user);
        teamUserList.add(teamUser);
        team.setTeamUsersByTeamId(teamUserList);
        teamUser.setTeamByTeamTeamId(team);
        teamDao.create(team);
        teamUserDao.create(teamUser);
    }

    @Transactional(readOnly = true)
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

    @Transactional
    public TeamInfoDto mapTeamInfo(Team team) {
        TeamInfoDto teamInfo = new TeamInfoDto(team);
        List<String> users = new ArrayList<String>();
        for (TeamUser teamUser : team.getTeamUsersByTeamId()) {
            users.add(teamUser.getUserByUserUserId().getNickname());
        }
        teamInfo.setUsers(users);
        teamInfo.setLeague(Leagues.valueOf(team.getLeagueByLeagueLeaguId().getLeagueName()));
        return teamInfo;
    }

    private League getLeagueFromGame(Game game, Leagues leagueType) throws ServiceException {
        for (League league : game.getLeaguesByGameId()) {
            if (league.getLeagueName().equals(leagueType.name())) {
                return league;
            }
        }
        throw new ServiceException("Cant load league: " + leagueType.toString());
    }

    @Transactional(readOnly = true)
    public List<TeamPickDto> getAllUserTeams(int userId){
        List<TeamPickDto> teamList = new ArrayList<TeamPickDto>();
        User user;
        try {
            user = userService.getUserById(userId);
        } catch (ServiceException e) {
            logger.error(e);
            return teamList;
        }
        for (TeamUser teamUser: user.getTeamUsersByUserId()){
            Team team = teamUser.getTeamByTeamTeamId();
            Game game = team.getLeagueByLeagueLeaguId().getGameByGameGameId();
            TeamPickDto teamPick = new TeamPickDto(team);
            teamPick.setNoTeamGame(game.noTeam());
            teamPick.setGameName(game.getGameName());
            teamList.add(teamPick);
        }
        return teamList;
    }


    @Transactional
    public void createTeamFromModel(int userId, NewTeamModel model) throws UserDoesntExists {
        League defaultLeague;
        User user;
        Game game;
        try {
            game = gameCache.getGame(model.getGameName());
            user = userService.getUserById(userId);
            defaultLeague = getLeagueFromGame(game, Leagues.getDefault());
        } catch (ServiceException e) {
            logger.error(e);
            return;
        }
        List<TeamUser> teamUserList = new ArrayList<TeamUser>();
        Team team = new Team();
        team.setName(model.getName());
        team.setDescription(model.getDescription());
        team.setRating(0);

        team.setLeagueByLeagueLeaguId(defaultLeague);

        //assign local user to team
        TeamUser teamUser = new TeamUser();
        teamUser.setUserByUserUserId(user);
        teamUser.setTeamByTeamTeamId(team);
        teamUserList.add(teamUser);

        //assign friends to team
        for (String userName : model.getUsers()) {
            User friend = userService.getUserByNickname(userName);
            teamUser = new TeamUser();
            teamUser.setUserByUserUserId(friend);
            teamUser.setTeamByTeamTeamId(team);
            teamUserList.add(teamUser);
        }

        team.setTeamUsersByTeamId(teamUserList);
        teamDao.create(team);
        for (TeamUser teamUserToSave : teamUserList) {
            teamUserDao.create(teamUserToSave);
        }
    }

    public Team getTeamById(int id) throws ServiceException {
        Team team =  teamDao.findById(id);
        if(team == null) {
            throw new ServiceException("Cannot find team with id: " + id);
        }
        return team;
    }
}
