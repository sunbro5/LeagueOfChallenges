package com.astora.web.dao.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * Created by to068466 on 29.10.2017.
 */
@Entity
@Table(name = "challenge_result", schema = "mydb", catalog = "")
public class ChallengeResult {
    private int challengeResultId;
    private int challengesChallengeId;
    private String score;
    private String description;
    private Timestamp created;
    private int winnerTeamId;
    private Byte draw;

    @Id
    @Column(name = "challenge_result_id")
    public int getChallengeResultId() {
        return challengeResultId;
    }

    public void setChallengeResultId(int challengeResultId) {
        this.challengeResultId = challengeResultId;
    }

    @Basic
    @Column(name = "Challenges_challenge_id")
    public int getChallengesChallengeId() {
        return challengesChallengeId;
    }

    public void setChallengesChallengeId(int challengesChallengeId) {
        this.challengesChallengeId = challengesChallengeId;
    }

    @Basic
    @Column(name = "score")
    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
    @Column(name = "winner_team_id")
    public int getWinnerTeamId() {
        return winnerTeamId;
    }

    public void setWinnerTeamId(int winnerTeamId) {
        this.winnerTeamId = winnerTeamId;
    }

    @Basic
    @Column(name = "draw")
    public Byte getDraw() {
        return draw;
    }

    public void setDraw(Byte draw) {
        this.draw = draw;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChallengeResult that = (ChallengeResult) o;

        if (challengeResultId != that.challengeResultId) return false;
        if (challengesChallengeId != that.challengesChallengeId) return false;
        if (winnerTeamId != that.winnerTeamId) return false;
        if (score != null ? !score.equals(that.score) : that.score != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (created != null ? !created.equals(that.created) : that.created != null) return false;
        if (draw != null ? !draw.equals(that.draw) : that.draw != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = challengeResultId;
        result = 31 * result + challengesChallengeId;
        result = 31 * result + (score != null ? score.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + winnerTeamId;
        result = 31 * result + (draw != null ? draw.hashCode() : 0);
        return result;
    }
}
