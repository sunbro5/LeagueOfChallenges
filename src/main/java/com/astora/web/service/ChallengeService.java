package com.astora.web.service;

import com.astora.web.dao.model.Challenge;
import com.astora.web.dto.ChallengeDto;
import com.astora.web.model.CreateChallengeModel;

import java.util.List;

public interface ChallengeService {

    void create(Challenge challenge);

    void create(CreateChallengeModel createChallengeModel);

    void update(Challenge challenge);

    Challenge findById(int id);

    List<Challenge> findAll();

    void delete(int id);

    List<ChallengeDto> prepareChallenges();
}
