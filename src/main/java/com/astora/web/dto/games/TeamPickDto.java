package com.astora.web.dto.games;

import com.astora.web.dao.model.Team;

import java.io.Serializable;

/**
 * @author <a href="mailto:maresjan694@gmail.com">Jan Mares</a>, 26.12.2017
 */
public class TeamPickDto implements Serializable{

    private int teamId;
    private String teamName;
    private String gameName;
    private boolean noTeamGame;

    public TeamPickDto(Team team) {
        this.teamId = team.getTeamId();
        this.teamName = team.getName();
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public boolean getNoTeamGame() {
        return noTeamGame;
    }

    public void setNoTeamGame(boolean noTeamGame) {
        this.noTeamGame = noTeamGame;
    }
}
