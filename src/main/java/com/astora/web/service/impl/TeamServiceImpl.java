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
import java.util.stream.Collectors;

@Service("teamService")
public class TeamServiceImpl implements TeamService {

    private static final Logger logger = Logger.getLogger(TeamServiceImpl.class);

    @Autowired
    private UserService userService;

    @Autowired
    private TeamDao teamDao;

    @Autowired
    private GameCache gameCache;

    @Autowired
    private TeamUserDao teamUserDao;

    @Transactional
    public void createDefaultTeam(int userId, String gameName) throws ServiceException {
        User user = userService.getUserById(userId);
        League defaultLeague;
        Game game;
        try {
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
        List<String> users = team.getTeamUsersByTeamId().stream()
                .map(teamUser -> teamUser.getUserByUserUserId().getNickname()).collect(Collectors.toList());
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
    public List<TeamPickDto> getAllUserTeams(int userId) throws ServiceException {
        List<TeamPickDto> teamList = new ArrayList<TeamPickDto>();
        User user = userService.getUserById(userId);

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
    public void createTeamFromModel(int userId, NewTeamModel model) throws ServiceException {
        League defaultLeague;
        User user = userService.getUserById(userId);
        Game game;
        try {
            game = gameCache.getGame(model.getGameName());
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

    @Transactional(readOnly = true)
    public Team getTeamById(int id) throws ServiceException {
        Team team =  teamDao.findById(id);
        if(team == null) {
            throw new ServiceException("Cannot find team with id: " + id);
        }
        return team;
    }

    public boolean isUserInTeam(User user, Team team){
        return team.getTeamUsersByTeamId().stream().map(TeamUser::getUserByUserUserId)
                .anyMatch(user1 -> user1.getUserId() == user.getUserId());
    }

}
