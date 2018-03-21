package com.astora.web.model;

import com.astora.web.enums.ChallengeResultState;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

/**
 * @author <a href="mailto:mares.jan@o2.cz">Jan Mares</a>, 12.3.2018
 */
public class ChallengeResultModel {

    public static final String MODEL_NAME = "challengeResultModel";

    private int challengeId;
    private int winnerTeamId;
    @Digits(fraction = 0, integer = 10)
    private String scoreChallenger;
    @Digits(fraction = 0, integer = 10)
    private String scoreOpponent;
    private int draw;
    private String state;

    public ChallengeResultModel() {
        draw = 0;
        state = ChallengeResultState.ACCEPTED.name();
    }

    public int getChallengeId() {
        return challengeId;
    }

    public void setChallengeId(int challengeId) {
        this.challengeId = challengeId;
    }

    public int getWinnerTeamId() {
        return winnerTeamId;
    }

    public void setWinnerTeamId(int winnerTeamId) {
        this.winnerTeamId = winnerTeamId;
    }

    public String getScoreChallenger() {
        return scoreChallenger;
    }

    public void setScoreChallenger(String scoreChallenger) {
        this.scoreChallenger = scoreChallenger;
    }

    public String getScoreOpponent() {
        return scoreOpponent;
    }

    public void setScoreOpponent(String scoreOpponent) {
        this.scoreOpponent = scoreOpponent;
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
