package com.astora.web.service;

import com.astora.web.dao.model.Team;

import java.util.List;

public interface TeamService {
    void create(Team team);

    void update(Team tean);

    Team findById(int id);

    List<Team> findAll();

    void delete(int id);
}
