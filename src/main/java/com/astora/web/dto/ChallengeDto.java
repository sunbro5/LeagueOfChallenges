package com.astora.web.dto;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class ChallengeDto {

    private int challengeId;
    private String coordsLat;
    private String coordsLng;
    private String text;
    private String challengeStart;
    private String challengeEnd;
    private String challengerTeamName;
    private int challengerTeamId;
    private int oponnentTeamId;
    private int gameId;
    private String gameName;

    public void setChallengeStart(String challengeStart) {
        this.challengeStart = challengeStart;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public void setChallengeEnd(String challengeEnd) {
        this.challengeEnd = challengeEnd;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getGameName() {
        return gameName;
    }


    public int getChallengeId() {
        return challengeId;
    }

    public void setChallengeId(int challengeId) {
        this.challengeId = challengeId;
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

    public String getChallengeStart() {
        return challengeStart;
    }

    public void setChallengeStart(Timestamp challengeStart) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd. MM. yyyy, HH:mm");
        this.challengeStart=simpleDateFormat.format(challengeStart);
    }

    public String getChallengeEnd() {
        return challengeEnd;
    }

    public void setChallengeEnd(Timestamp challengeEnd) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd. MM. yyyy, HH:mm");
        this.challengeEnd = simpleDateFormat.format(challengeEnd);
    }

    public String getChallengerTeamName() {
        return challengerTeamName;
    }

    public void setChallengerTeamName(String challengerTeamName) {
        this.challengerTeamName = challengerTeamName;
    }

    public int getChallengerTeamId() {
        return challengerTeamId;
    }

    public void setChallengerTeamId(int challengerTeamId) {
        this.challengerTeamId = challengerTeamId;
    }

    public int getOponnentTeamId() {
        return oponnentTeamId;
    }

    public void setOponnentTeamId(int oponnentTeamId) {
        this.oponnentTeamId = oponnentTeamId;
    }
}
