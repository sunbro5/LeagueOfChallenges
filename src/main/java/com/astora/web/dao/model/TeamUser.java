package com.astora.web.dao.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by to068466 on 29.10.2017.
 */
@Entity
@Table(name = "team_user", schema = "mydb", catalog = "")
public class TeamUser {
    private int teamUserId;
    private int teamTeamId;
    private int userUserId;

    @Id
    @Column(name = "team_user_id")
    public int getTeamUserId() {
        return teamUserId;
    }

    public void setTeamUserId(int teamUserId) {
        this.teamUserId = teamUserId;
    }

    @Basic
    @Column(name = "team_team_id")
    public int getTeamTeamId() {
        return teamTeamId;
    }

    public void setTeamTeamId(int teamTeamId) {
        this.teamTeamId = teamTeamId;
    }

    @Basic
    @Column(name = "user_user_id")
    public int getUserUserId() {
        return userUserId;
    }

    public void setUserUserId(int userUserId) {
        this.userUserId = userUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeamUser teamUser = (TeamUser) o;

        if (teamUserId != teamUser.teamUserId) return false;
        if (teamTeamId != teamUser.teamTeamId) return false;
        if (userUserId != teamUser.userUserId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = teamUserId;
        result = 31 * result + teamTeamId;
        result = 31 * result + userUserId;
        return result;
    }
}
