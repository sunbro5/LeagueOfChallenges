package com.astora.web.model;

import java.sql.Timestamp;

public class CreateChallengeModel {
    private int challengerTeamId;
    private Timestamp challengeStart;
    private Timestamp challengeEnd;
    private String coordsLat;
    private String coordsLng;


    public Timestamp getChallengeStart() {
        return challengeStart;
    }

    public void setChallengeStart(Timestamp challengeStart) {
        this.challengeStart = challengeStart;
    }

    public Timestamp getChallengeEnd() {
        return challengeEnd;
    }

    public void setChallengeEnd(Timestamp challengeEnd) {
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

    private String text;
}
