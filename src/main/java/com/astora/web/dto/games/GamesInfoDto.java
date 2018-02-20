package com.astora.web.dto.games;

import com.astora.web.dao.model.Game;

import java.io.Serializable;

/**
 * @author <a href="mailto:maresjan694@gmail.com">Jan Mares</a>, 8.12.2017
 */
public class GamesInfoDto {

    private String gameName;
    private String gameDescription;
    private long teamsCount;

    public GamesInfoDto(Game game) {
        this.gameName = game.getGameName();
        this.gameDescription = game.getGameDescription();
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getGameDescription() {
        return gameDescription;
    }

    public void setGameDescription(String gameDescription) {
        this.gameDescription = gameDescription;
    }

    public long getTeamsCount() {
        return teamsCount;
    }

    public void setTeamsCount(long teamsCount) {
        this.teamsCount = teamsCount;
    }
}
