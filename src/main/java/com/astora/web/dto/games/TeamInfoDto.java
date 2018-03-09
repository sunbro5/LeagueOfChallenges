package com.astora.web.dto.games;

import com.astora.web.dao.model.Game;
import com.astora.web.dao.model.Team;
import com.astora.web.enums.Leagues;

import java.io.Serializable;
import java.util.List;

/**
 * @author <a href="mailto:maresjan694@gmail.com">Jan Mares</a>, 10.12.2017
 */
public class TeamInfoDto {

    private int teamId;
    private String name;
    private String description;
    private Leagues league;
    private List<String> users;

    public TeamInfoDto(Team team) {
        this.name = team.getName();
        this.description = team.getDescription();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Leagues getLeague() {
        return league;
    }

    public void setLeague(Leagues league) {
        this.league = league;
    }

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }
}
