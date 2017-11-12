package com.astora.web.model;

import java.util.Date;

public class CreateChallengeModel {

    private Integer challengerTeamId;
    private Date challengeStart;
    private Date challengeEnd;
    private String coordsLat;
    private String coordsLng;
    private String text;

    public Integer getChallengerTeamId() {
        return challengerTeamId;
    }

    public void setChallengerTeamId(Integer challengerTeamId) {
        this.challengerTeamId = challengerTeamId;
    }

    public Date getChallengeStart() {
        return challengeStart;
    }

    public void setChallengeStart(Date challengeStart) {
        this.challengeStart = challengeStart;
    }

    public Date getChallengeEnd() {
        return challengeEnd;
    }

    public void setChallengeEnd(Date challengeEnd) {
        this.challengeEnd = challengeEnd;
    }

    public String getCoordsLat() {
        return coordsLat;
    }

    public void setCoordsLat(String coordsLat) {
        this.coordsLat = coordsLat;
    }

    public String getCoordsLng() {
        return coordsLng;
    }

    public void setCoordsLng(String coordsLng) {
        this.coordsLng = coordsLng;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
