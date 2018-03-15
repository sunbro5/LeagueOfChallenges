package com.astora.web.model;

import com.astora.web.enums.ChallengeResultState;

import javax.validation.constraints.NotNull;

/**
 * @author <a href="mailto:mares.jan@o2.cz">Jan Mares</a>, 12.3.2018
 */
public class ChallengeResultModel {

    public static final String MODEL_NAME = "challengeResultModel";

    private int challengeId;
    private int winnerTeamId;
    @NotNull(message = "ChallengeResultModel.scoreWinner.empty")
    private int scoreWinner;
    @NotNull(message = "ChallengeResultModel.scoreLooser.empty")
    private int scoreLooser;
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

    public int getScoreWinner() {
        return scoreWinner;
    }

    public void setScoreWinner(int scoreWinner) {
        this.scoreWinner = scoreWinner;
    }

    public int getScoreLooser() {
        return scoreLooser;
    }

    public void setScoreLooser(int scoreLooser) {
        this.scoreLooser = scoreLooser;
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
