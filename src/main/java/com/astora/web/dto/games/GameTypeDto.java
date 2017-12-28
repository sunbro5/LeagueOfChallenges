package com.astora.web.dto.games;

/**
 * @author <a href="mailto:maresjan694@gmail.com">Jan Mares</a>, 20.12.2017
 */
public class GameTypeDto {

    private String gameName;
    private boolean noTeamGame;

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
