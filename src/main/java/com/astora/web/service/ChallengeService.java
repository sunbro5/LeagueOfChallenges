package com.astora.web.service;

import com.astora.web.dao.model.Challenge;

import java.util.List;

public interface ChallengeService {

    void create(Challenge challenge);

    void update(Challenge challenge);

    Challenge findById(int id);

    List<Challenge> findAll();

    void delete(int id);
}
