package com.astora.web.dao.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Created by to068466 on 29.10.2017.
 */
@Entity
public class Challenge {
    private int challengeId;
    private int challengerTeamId;
    private Integer oponnentTeamId;
    private String coords;
    private Timestamp challengeStart;
    private Timestamp challengeEnd;
    private String text;

    @Id
    @Column(name = "challenge_id")
    public int getChallengeId() {
        return challengeId;
    }

    public void setChallengeId(int challengeId) {
        this.challengeId = challengeId;
    }

    @Basic
    @Column(name = "challenger_team_id")
    public int getChallengerTeamId() {
        return challengerTeamId;
    }

    public void setChallengerTeamId(int challengerTeamId) {
        this.challengerTeamId = challengerTeamId;
    }

    @Basic
    @Column(name = "oponnent_team_id")
    public Integer getOponnentTeamId() {
        return oponnentTeamId;
    }

    public void setOponnentTeamId(Integer oponnentTeamId) {
        this.oponnentTeamId = oponnentTeamId;
    }

    @Basic
    @Column(name = "Coords")
    public String getCoords() {
        return coords;
    }

    public void setCoords(String coords) {
        this.coords = coords;
    }

    @Basic
    @Column(name = "Challenge_start")
    public Timestamp getChallengeStart() {
        return challengeStart;
    }

    public void setChallengeStart(Timestamp challengeStart) {
        this.challengeStart = challengeStart;
    }

    @Basic
    @Column(name = "Challenge_end")
    public Timestamp getChallengeEnd() {
        return challengeEnd;
    }

    public void setChallengeEnd(Timestamp challengeEnd) {
        this.challengeEnd = challengeEnd;
    }

    @Basic
    @Column(name = "Text")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Challenge challenge = (Challenge) o;

        if (challengeId != challenge.challengeId) return false;
        if (challengerTeamId != challenge.challengerTeamId) return false;
        if (oponnentTeamId != null ? !oponnentTeamId.equals(challenge.oponnentTeamId) : challenge.oponnentTeamId != null)
            return false;
        if (coords != null ? !coords.equals(challenge.coords) : challenge.coords != null) return false;
        if (challengeStart != null ? !challengeStart.equals(challenge.challengeStart) : challenge.challengeStart != null)
            return false;
        if (challengeEnd != null ? !challengeEnd.equals(challenge.challengeEnd) : challenge.challengeEnd != null)
            return false;
        if (text != null ? !text.equals(challenge.text) : challenge.text != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = challengeId;
        result = 31 * result + challengerTeamId;
        result = 31 * result + (oponnentTeamId != null ? oponnentTeamId.hashCode() : 0);
        result = 31 * result + (coords != null ? coords.hashCode() : 0);
        result = 31 * result + (challengeStart != null ? challengeStart.hashCode() : 0);
        result = 31 * result + (challengeEnd != null ? challengeEnd.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }
}
