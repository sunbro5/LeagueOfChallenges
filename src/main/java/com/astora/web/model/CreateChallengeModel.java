package com.astora.web.model;

public class CreateChallengeModel {

    private Integer challengerTeamId;
    private String challengeStart;
    private String challengeEnd;
    private String coordsLat;
    private String coordsLng;
    private String text;
    private String gameName;

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public Integer getChallengerTeamId() {
        return challengerTeamId;
    }

    public void setChallengerTeamId(Integer challengerTeamId) {
        this.challengerTeamId = challengerTeamId;
    }

    public String getChallengeStart() {
        return challengeStart;
    }

    public void setChallengeStart(String challengeStart) {
        this.challengeStart = challengeStart;
    }

    public String getChallengeEnd() {
        return challengeEnd;
    }

    public void setChallengeEnd(String challengeEnd) {
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
