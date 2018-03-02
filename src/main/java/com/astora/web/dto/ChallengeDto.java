package com.astora.web.dto;

import com.astora.web.enums.ChallengeState;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChallengeDto {

    private int challengeId;
    private String coordsLat;
    private String coordsLng;
    private ChallengeState state;
    private String text;
    private Date challengeStart;
    private Date challengeEnd;
    private String challengerTeamName;
    private int challengerTeamId;
    private int opponentTeamId;
    private int gameId;
    private String gameName;

    public void setGameName(String gameName) {
        this.gameName = gameName;
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

    public int getOpponentTeamId() {
        return opponentTeamId;
    }

    public void setOpponentTeamId(int opponentTeamId) {
        this.opponentTeamId = opponentTeamId;
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

    public ChallengeState getState() {
        return state;
    }

    public void setState(ChallengeState state) {
        this.state = state;
    }
}
