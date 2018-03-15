package com.astora.web.service;

import com.astora.web.dto.ChallengeDto;
import com.astora.web.dto.ChallengeInfoDto;
import com.astora.web.exception.ServiceException;
import com.astora.web.model.ChallengeResultModel;
import com.astora.web.model.CreateChallengeModel;

import java.util.List;

public interface ChallengeService {

    List<ChallengeDto> prepareChallenges();

    void createChallenge(CreateChallengeModel createChallengeModel) throws ServiceException;

    List<ChallengeDto> getAllActiveChallenges();

    List<ChallengeDto> getUserChallenges(int userId) throws ServiceException;

    void cancelChallenge(int userId, int challengeId) throws ServiceException;

    ChallengeInfoDto getChallengeDetail(int challengeId) throws ServiceException;

    void joinChallenge(int userId, int teamId, int challengeId) throws ServiceException;

    void acceptChallenge(int userId, int challengeId) throws ServiceException;

    void declineChallenge(int userId, int challengeId) throws ServiceException;

    boolean isUserChallenge(int userId, int challengeId) throws ServiceException;

    boolean isOpponentChallenge(int userId, int challengeId) throws ServiceException;

    List<ChallengeDto> getOpponentChallenges(int userId) throws ServiceException;

    void saveChallengeResult(int userId, ChallengeResultModel challengeResultModel) throws ServiceException;

    /**
     * Return true if some user from team already created some Challenge Result
     * @param userId
     * @param challengeId
     * @return
     * @throws ServiceException
     */
    boolean canUserSetResult(int userId, int challengeId) throws ServiceException;

}
