package com.astora.web.dao.model;

import javax.persistence.*;

@Entity
@Table(name = "team_user", schema = "leagueofchallenges")
public class TeamUser {
    private int teamUserId;
    private Team teamByTeamTeamId;
    private User userByUserUserId;

    @Id
    @Column(name = "team_user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @ManyToOne
    @JoinColumn(name = "team_team_id", referencedColumnName = "team_id", nullable = false)
    public Team getTeamByTeamTeamId() {
        return teamByTeamTeamId;
    }

    public void setTeamByTeamTeamId(Team teamByTeamTeamId) {
        this.teamByTeamTeamId = teamByTeamTeamId;
    }

    @ManyToOne
    @JoinColumn(name = "user_user_id", referencedColumnName = "user_id", nullable = false)
    public User getUserByUserUserId() {
        return userByUserUserId;
    }

    public void setUserByUserUserId(User userByUserUserId) {
        this.userByUserUserId = userByUserUserId;
    }
}
