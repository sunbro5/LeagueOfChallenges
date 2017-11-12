package com.astora.web.service;

import com.astora.web.dao.model.Team;

import java.util.List;

public interface TeamUserService {
    List<Team> getAllTeamsForUser(int userId);
}
