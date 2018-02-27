package com.astora.web.service;

import com.astora.web.dto.ChallengeDto;
import com.astora.web.exception.ServiceException;
import com.astora.web.model.CreateChallengeModel;

import java.util.List;

public interface ChallengeService {

    List<ChallengeDto> prepareChallenges();

    void createChallenge(CreateChallengeModel createChallengeModel) throws ServiceException;

    List<ChallengeDto> getAllActiveChallenges();

    List<ChallengeDto> getUserChallenges(int userId) throws ServiceException;

    void cancelChallenge(int userId, int challengeId) throws ServiceException;
}
