package com.astora.web.service;

import com.astora.web.dao.model.Team;
import com.astora.web.dao.model.User;
import com.astora.web.dto.games.GameTypeDto;
import com.astora.web.dto.games.TeamInfoDto;
import com.astora.web.dto.games.TeamPickDto;
import com.astora.web.exception.ServiceException;
import com.astora.web.exception.UserDoesntExists;
import com.astora.web.model.NewTeamModel;
import javafx.util.Pair;

import java.util.List;

public interface TeamService {

    List<TeamInfoDto> getTeamsByGameName(int userId, String gameName) throws ServiceException;

    void createDefaultTeam(int userId, String gameName) throws ServiceException;

    void createTeamFromModel(int userId, NewTeamModel model) throws ServiceException;

    List<TeamPickDto> getAllUserTeams(int userId) throws ServiceException;

    List<GameTypeDto> getAllUserGames(int userId) throws ServiceException;

    Team getTeamById(int id) throws ServiceException;

    boolean isUserInTeam(User user, Team team);

    /**
     * Return 0 if team is trustworth or return number count from users rating
     * @param teamId
     * @return
     * @throws ServiceException
     */
    int usersTrustWorthValue(int teamId) throws ServiceException;

    void punishAllCheatersMethod(Team team, int value) throws ServiceException;

}
