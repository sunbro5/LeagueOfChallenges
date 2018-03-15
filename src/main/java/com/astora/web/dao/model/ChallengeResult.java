package com.astora.web.dao.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by to068466 on 29.10.2017.
 */
@Entity
public class ChallengeResult {
    private int challengeResultId;
    private int scoreWinner;
    private int scoreLooser;
    private String state;
    private Timestamp created;
    private int draw;
    private Challenge challengeByChallengesChallengeId;
    private Team teamByWinnerTeamId;
    private Team creator;

    @Id
    @Column(name = "challenge_result_id")
    public int getChallengeResultId() {
        return challengeResultId;
    }

    public void setChallengeResultId(int challengeResultId) {
        this.challengeResultId = challengeResultId;
    }

    @Basic
    @Column(name = "scoreWinner")
    public int getScoreWinner() {
        return scoreWinner;
    }

    public void setScoreWinner(int scoreWinner) {
        this.scoreWinner = scoreWinner;
    }

    @Basic
    @Column(name = "scoreLooser")
    public int getScoreLooser() {
        return scoreLooser;
    }

    public void setScoreLooser(int scoreLooser) {
        this.scoreLooser = scoreLooser;
    }

    @Basic
    @Column(name = "state")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Basic
    @Column(name = "created")
    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    @Basic
    @Column(name = "draw")
    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    @ManyToOne
    @JoinColumn(name = "Challenges_challenge_id", referencedColumnName = "team_id", nullable = false)
    public Team getCreator() {
        return creator;
    }

    public void setCreator(Team creator) {
        this.creator = creator;
    }

    @ManyToOne
    @JoinColumn(name = "Challenges_challenge_id", referencedColumnName = "challenge_id", nullable = false)
    public Challenge getChallengeByChallengesChallengeId() {
        return challengeByChallengesChallengeId;
    }

    public void setChallengeByChallengesChallengeId(Challenge challengeByChallengesChallengeId) {
        this.challengeByChallengesChallengeId = challengeByChallengesChallengeId;
    }

    @ManyToOne
    @JoinColumn(name = "winner_team_id", referencedColumnName = "team_id", nullable = false)
    public Team getTeamByWinnerTeamId() {
        return teamByWinnerTeamId;
    }

    public void setTeamByWinnerTeamId(Team teamByWinnerTeamId) {
        this.teamByWinnerTeamId = teamByWinnerTeamId;
    }
}
