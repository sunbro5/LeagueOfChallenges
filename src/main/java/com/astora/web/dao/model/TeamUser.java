package com.astora.web.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "team_user", schema = "mydb", catalog = "")
public class TeamUser {
    private int teamUserId;

    @Id
    @Column(name = "team_user_id")
    public int getTeamUserId() {
        return teamUserId;
    }

    public void setTeamUserId(int teamUserId) {
        this.teamUserId = teamUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeamUser teamUser = (TeamUser) o;

        if (teamUserId != teamUser.teamUserId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return teamUserId;
    }
}
