package com.astora.web.dao.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by to068466 on 29.10.2017.
 */
@Entity
@Table(name = "Challenge_Result")
public class ChallengeResult {

    private int challengeResultId;
    private int scoreChallenger;
    private int scoreOpponent;
    private String state;
    private Timestamp created;
    private int draw;
    private Challenge challengeByChallengesChallengeId;
    private Team teamByWinnerTeamId;
    private Team creator;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "challenge_result_id")
    public int getChallengeResultId() {
        return challengeResultId;
    }

    public void setChallengeResultId(int challengeResultId) {
        this.challengeResultId = challengeResultId;
    }

    @Basic
    @Column(name = "scoreChallenger")
    public int getScoreChallenger() {
        return scoreChallenger;
    }

    public void setScoreChallenger(int scoreChallenger) {
        this.scoreChallenger = scoreChallenger;
    }

    @Basic
    @Column(name = "scoreOpponent")
    public int getScoreOpponent() {
        return scoreOpponent;
    }

    public void setScoreOpponent(int scoreOpponent) {
        this.scoreOpponent = scoreOpponent;
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
    @Column(name = "created", insertable = false, updatable = false)
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
    @JoinColumn(name = "Team_team_id", referencedColumnName = "team_id", nullable = false)
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
