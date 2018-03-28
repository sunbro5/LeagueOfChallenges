package com.astora.web.dto.challenge;

/**
 * @author <a href="mailto:mares.jan@o2.cz">Jan Mares</a>, 27.3.2018
 */
public class ChallengeResultDto {

    private int scoreChallenger;
    private int scoreOpponent;
    private int draw;
    private String winnerTeamName;

    public int getScoreChallenger() {
        return scoreChallenger;
    }

    public void setScoreChallenger(int scoreChallenger) {
        this.scoreChallenger = scoreChallenger;
    }

    public int getScoreOpponent() {
        return scoreOpponent;
    }

    public void setScoreOpponent(int scoreOpponent) {
        this.scoreOpponent = scoreOpponent;
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public String getWinnerTeamName() {
        return winnerTeamName;
    }

    public void setWinnerTeamName(String winnerTeamName) {
        this.winnerTeamName = winnerTeamName;
    }
}
