package com.astora.web.service.impl;

import com.astora.web.dao.ChallengeDao;
import com.astora.web.dao.ChallengeResultDao;
import com.astora.web.dao.TeamDao;
import com.astora.web.dao.model.*;
import com.astora.web.dto.challenge.ChallengeDto;
import com.astora.web.dto.challenge.ChallengeInfoDto;
import com.astora.web.enums.ChallengeResultState;
import com.astora.web.enums.ChallengeState;
import com.astora.web.exception.ServiceException;
import com.astora.web.exception.UserConflictException;
import com.astora.web.mapper.ChallengeMapper;
import com.astora.web.mapper.ChallengeResultMapper;
import com.astora.web.model.ChallengeResultModel;
import com.astora.web.model.CreateChallengeModel;
import com.astora.web.service.ChallengeService;
import com.astora.web.service.PropertyService;
import com.astora.web.service.TeamService;
import com.astora.web.service.UserService;
import com.astora.web.utils.CustomValidationUtils;
import com.astora.web.utils.DateUtil;
import org.apache.log4j.Logger;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service("challengeService")
public class ChallengeServiceImpl implements ChallengeService {

    private static final Logger logger = Logger.getLogger(ChallengeServiceImpl.class);
    private ChallengeMapper challengeMapper = Mappers.getMapper(ChallengeMapper.class);
    private ChallengeResultMapper challengeResultMapper = Mappers.getMapper(ChallengeResultMapper.class);

    @Autowired
    private ChallengeDao challengeDao;

    @Autowired
    private TeamService teamService;

    @Autowired
    private UserService userService;

    @Autowired
    private TeamDao teamDao;

    @Autowired
    private ChallengeResultDao challengeResultDao;

    @Transactional
    public List<ChallengeDto> prepareChallenges() {
        List<ChallengeDto> challenges = new ArrayList<ChallengeDto>();

        for (Challenge challenge : challengeDao.findAll()) {
            ChallengeDto challengeDto = new ChallengeDto();
            challengeDto.setChallengeId(challenge.getChallengeId());
            challengeDto.setChallengeStart((challenge.getChallengeStart()));
            challengeDto.setChallengeEnd(challenge.getChallengeEnd());
            challengeDto.setCoordsLat(challenge.getCoordsLat());
            challengeDto.setCoordsLng(challenge.getCoordsLng());
            challengeDto.setChallengerTeamName(challenge.getTeamByChallengerTeamId().getName());
            challengeDto.setChallengerTeamId(challenge.getTeamByChallengerTeamId().getTeamId());
            if (challenge.getTeamByOponnentTeamId() != null) {
                challengeDto.setOpponentTeamId(challenge.getTeamByOponnentTeamId().getTeamId());
            }
            challengeDto.setText(challenge.getText());
            Game game = challenge.getTeamByChallengerTeamId().getLeagueByLeagueLeaguId().getGameByGameGameId();
            challengeDto.setGameName(game.getGameName());
            challengeDto.setGameId(game.getGameId());
            challenges.add(challengeDto);
        }

        return challenges;
    }

    @Transactional
    public void createChallenge(CreateChallengeModel createChallengeModel) throws ServiceException {
        Team team = teamService.getTeamById(createChallengeModel.getChallengerTeamId());
        Challenge challenge = new Challenge();
        challenge.setState(ChallengeState.CREATED);
        challenge.setText(createChallengeModel.getText());
        challenge.setCoordsLat(createChallengeModel.getCoordsLat());
        challenge.setCoordsLng(createChallengeModel.getCoordsLng());
        challenge.setTeamByChallengerTeamId(team);
        challenge.setChallengeStart(DateUtil.convertToTimestamp(createChallengeModel.getChallengeStart()));
        challenge.setChallengeEnd(DateUtil.convertToTimestamp(createChallengeModel.getChallengeEnd()));
        challengeDao.create(challenge);
    }


    @Transactional(readOnly = true)
    public List<ChallengeDto> getAllActiveChallenges() {
        List<ChallengeDto> challenges = new ArrayList<ChallengeDto>();
        List<Challenge> activeChallenges = challengeDao.getActiveChallenges();
        activeChallenges.stream().forEach(challenge -> {
            ChallengeDto challengeDto = challengeMapper.challengeToChallengeDto(challenge,
                    challenge.getTeamByChallengerTeamId().getLeagueByLeagueLeaguId().getGameByGameGameId(),
                    challenge.getTeamByChallengerTeamId(), challenge.getTeamByOponnentTeamId());

            challenges.add(challengeDto);
        });
        return challenges;
    }

    @Transactional(readOnly = true)
    public ChallengeInfoDto getChallengeDetail(int challengeId) throws ServiceException {
        Challenge challenge = challengeDao.findById(challengeId);
        if (challenge == null) {
            throw new ServiceException("Challenge with id: " + challengeId + " does not exists.");
        }
        ChallengeInfoDto challengeInfoDto = challengeMapper.challengeToChallengeInfoDto(challenge,
                challenge.getTeamByChallengerTeamId().getLeagueByLeagueLeaguId().getGameByGameGameId(),
                challenge.getTeamByChallengerTeamId(), challenge.getTeamByOponnentTeamId());

        challengeInfoDto.setChallengers(mapTeamUsersToNicknames(challenge.getTeamByChallengerTeamId().getTeamUsersByTeamId()));
        if (challenge.getTeamByOponnentTeamId() != null) {
            challengeInfoDto.setOpponents(mapTeamUsersToNicknames(challenge.getTeamByOponnentTeamId().getTeamUsersByTeamId()));
        }
        return challengeInfoDto;
    }

    @Transactional(readOnly = true)
    public List<ChallengeDto> getUserChallenges(int userId) throws ServiceException {
        User user = userService.getUserById(userId);
        List<ChallengeDto> challenges = new ArrayList<>();
        user.getTeamUsersByUserId().stream()
                .map(TeamUser::getTeamByTeamTeamId)
                .map(challengeDao::getChallengeForTeam)
                .forEach(challenges1 -> challenges1
                        .forEach(challenge -> challenges.add(challengeMapper.challengeToChallengeDto(challenge,
                                challenge.getTeamByChallengerTeamId().getLeagueByLeagueLeaguId().getGameByGameGameId(),
                                challenge.getTeamByChallengerTeamId(), challenge.getTeamByOponnentTeamId()))));

        challenges.sort((o1, o2) -> o1.getChallengeStart().compareTo(o2.getChallengeStart()));
        return challenges;
    }

    @Transactional(readOnly = true)
    public List<ChallengeDto> getOpponentChallenges(int userId) throws ServiceException {
        User user = userService.getUserById(userId);
        List<ChallengeDto> challenges = new ArrayList<>();
        user.getTeamUsersByUserId().stream()
                .map(TeamUser::getTeamByTeamTeamId)
                .map(challengeDao::getChallengeOpponentTeam)
                .forEach(challenges1 -> challenges1
                        .forEach(challenge -> challenges.add(challengeMapper.challengeToChallengeDto(challenge,
                                challenge.getTeamByChallengerTeamId().getLeagueByLeagueLeaguId().getGameByGameGameId(),
                                challenge.getTeamByChallengerTeamId(), challenge.getTeamByOponnentTeamId()))));
        challenges.sort((o1, o2) -> o1.getChallengeStart().compareTo(o2.getChallengeStart()));
        return challenges;
    }

    @Transactional
    public void cancelChallenge(int userId, int challengeId) throws ServiceException {
        User user = userService.getUserById(userId);
        Challenge challenge = challengeDao.findById(challengeId);
        validateChallenge(challenge, ChallengeState.CREATED, challengeId);
        validateTeamUser(challenge, user, true);
        challengeDao.delete(challengeId);
    }

    @Transactional
    public void joinChallenge(int userId, int teamId, int challengeId) throws ServiceException {
        User user = userService.getUserById(userId);
        Team team = teamService.getTeamById(teamId);
        if (!teamService.isUserInTeam(user, team)) {
            throw new ServiceException(String.format("User nickname: %s is not in team teamName: %s", user.getNickname(), team.getName()));
        }
        Challenge challenge = challengeDao.findById(challengeId);
        validateChallenge(challenge, ChallengeState.CREATED, challengeId);
        validateOpponent(challenge, team);
        challenge.setTeamByOponnentTeamId(team);
        challenge.setState(ChallengeState.CHALLENGED);
        challengeDao.update(challenge);
    }

    @Transactional
    public void acceptChallenge(int userId, int challengeId) throws ServiceException {
        setChallengeState(userId, challengeId, ChallengeState.CHALLENGED, ChallengeState.ACCEPTED);
    }

    @Transactional
    public void declineChallenge(int userId, int challengeId) throws ServiceException {
        setChallengeState(userId, challengeId, ChallengeState.CHALLENGED, ChallengeState.CREATED);
    }

    private void setChallengeState(int userId, int challengeId, ChallengeState oldState, ChallengeState newState) throws ServiceException {
        User user = userService.getUserById(userId);
        Challenge challenge = challengeDao.findById(challengeId);
        validateChallenge(challenge, oldState, challengeId);
        validateTeamUser(challenge, user, true);
        challenge.setState(newState);
        challengeDao.update(challenge);
    }

    private void validateChallenge(Challenge challenge, ChallengeState challengeState, int challengeId) throws ServiceException {
        if (challenge == null || challenge.getState() != (challengeState)) {
            throw new ServiceException("Challenge does not exists or is in wrong state. Id: " + challengeId);
        }
    }

    /**
     * validate challenge that user is challenger or opponent
     *
     * @param challenge
     * @param user
     */
    private void validateTeamUser(Challenge challenge, User user, boolean isChallenger) throws ServiceException {
        Collection<TeamUser> list;
        if (isChallenger) {
            list = challenge.getTeamByChallengerTeamId().getTeamUsersByTeamId();
        } else {
            list = challenge.getTeamByOponnentTeamId().getTeamUsersByTeamId();
        }
        if (!isUserInTeam(list, user)) {
            throw new ServiceException(String.format("Challenge id: %s is not defined for User nickname: %s",
                    challenge.getChallengeId(), user.getNickname()));
        }
    }

    private void validateOpponent(Challenge challenge, Team team) throws ServiceException {
        for (TeamUser userOpponent : team.getTeamUsersByTeamId()) {
            for (TeamUser userChallenger : challenge.getTeamByChallengerTeamId().getTeamUsersByTeamId()) {
                if (userOpponent.getUserByUserUserId().getUserId() == userChallenger.getUserByUserUserId().getUserId()) {
                    throw new UserConflictException("User from team is already in challenger team",
                            userOpponent.getUserByUserUserId().getNickname());
                }
            }
        }
    }

    private boolean isUserInTeam(Collection<TeamUser> list, User user) {
        return list.stream().map(TeamUser::getUserByUserUserId)
                .anyMatch(user1 -> user.getUserId() == user1.getUserId());
    }

    private List<String> mapTeamUsersToNicknames(Collection<TeamUser> list) {
        return list.stream().map(TeamUser::getUserByUserUserId).map(User::getNickname).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public boolean isUserChallenge(int userId, int challengeId) throws ServiceException {
        User user = userService.getUserById(userId);
        Challenge challenge = challengeDao.findById(challengeId);
        return challenge.getTeamByChallengerTeamId().getTeamUsersByTeamId().
                stream().map(TeamUser::getUserByUserUserId).anyMatch(user1 -> user1.getUserId() == user.getUserId());
    }

    @Transactional(readOnly = true)
    public boolean isOpponentChallenge(int userId, int challengeId) throws ServiceException {
        User user = userService.getUserById(userId);
        Challenge challenge = challengeDao.findById(challengeId);
        if (challenge.getTeamByOponnentTeamId() == null) {
            return false;
        }
        return challenge.getTeamByOponnentTeamId().getTeamUsersByTeamId().
                stream().map(TeamUser::getUserByUserUserId).anyMatch(user1 -> user1.getUserId() == user.getUserId());
    }

    @Transactional(readOnly = true)
    public boolean canUserSetResult(int userId, int challengeId) throws ServiceException {
        User user = userService.getUserById(userId);
        Challenge challenge = challengeDao.findById(challengeId);
        if (challenge == null) {
            throw new ServiceException("There is no challenge for challenge id: " + challengeId);
        }
        if (!challenge.getState().equals(ChallengeState.ACCEPTED.name())) {
            return false;
        }
        Team team = getTeamFromChallenge(user, challenge);
        return !challenge.getChallengeResultsByChallengeId().stream().map(ChallengeResult::getCreator).
                anyMatch(team1 -> team1.getTeamId() == team.getTeamId());

    }

    /**
     * Some magic for process challenge to FINISHED
     *
     * @param challenge
     * @throws ServiceException
     */
    public void processChallengeSave(Challenge challenge) throws ServiceException {

        validateProcessChallenge(challenge);
        List<ChallengeResult> results = new ArrayList<>(challenge.getChallengeResultsByChallengeId());
        //check if results are same
        if (checkResultValidity(results)) {
            if (checkScoreValidity(results)) {
                //different score but its ok people make mistakes
                setChallengeResultsState(results, ChallengeResultState.DIFFERENT_SCORE);
            } else {
                //score and winners are same lets Accept this
                setChallengeResultsState(results, ChallengeResultState.ACCEPTED);
            }
        } else {
            //some fuc*** set false score !!!! lets decide who will get rekt
            processFalseScore(challenge, results);
        }
        saveChallengeResults(results);
        challenge.setState(ChallengeState.FINISHED);
        challengeDao.update(challenge);
    }

    public void saveChallengeResults(List<ChallengeResult> results){
        results.forEach(challengeResultDao::update);
    }

    public void processFalseScore(Challenge challenge, List<ChallengeResult> results) throws ServiceException {

        Team challenger = challenge.getTeamByChallengerTeamId();
        Team opponent = challenge.getTeamByOponnentTeamId();

        int challengerTrustValue = teamService.usersTrustWorthValue(challenger.getTeamId());
        int opponentTrustValue = teamService.usersTrustWorthValue(opponent.getTeamId());

        if (challengerTrustValue == opponentTrustValue) {
            //cant decide who is cheater
            setFalseChallengeAndRating(challenge, results);
        } else if (challengerTrustValue > opponentTrustValue) {
            if (challengerTrustValue > opponentTrustValue + PropertyService.TRUST_DECIDER_KOEFICIENT) {
                setChallengeByTrustedTeam(challenger, opponent, challenge);
            } else {
                // well too little difference between team users ratings
                setFalseChallengeAndRating(challenge, results);
            }
        } else {
            if (opponentTrustValue > challengerTrustValue + PropertyService.TRUST_DECIDER_KOEFICIENT) {
                setChallengeByTrustedTeam(opponent, challenger, challenge);
            } else {
                // well too little difference between team users ratings
                setFalseChallengeAndRating(challenge, results);
            }
        }
    }

    private void setChallengeByTrustedTeam(Team trustedTeam, Team falseTeam, Challenge challenge) throws ServiceException {
        for (ChallengeResult result : challenge.getChallengeResultsByChallengeId()) {
            if (result.getCreator().getTeamId() == trustedTeam.getTeamId()) {
                result.setState(ChallengeResultState.ACCEPTED);
            } else {
                result.setState(ChallengeResultState.FALSE);
                teamService.punishAllCheatersMethod(falseTeam, -10);
            }
        }
    }


    private void setFalseChallengeAndRating(Challenge challenge, List<ChallengeResult> results) throws ServiceException {
        setChallengeResultsState(results, ChallengeResultState.FALSE);
        // give them bad rating -1, its not fair but someone must get it
        teamService.punishAllCheatersMethod(challenge.getTeamByChallengerTeamId(), -1);
        teamService.punishAllCheatersMethod(challenge.getTeamByOponnentTeamId(), -1);
    }

    private boolean validateProcessChallenge(Challenge challenge) throws ServiceException {
        Collection<ChallengeResult> results = challenge.getChallengeResultsByChallengeId();
        if (CustomValidationUtils.isEmpty(results)) {
            logger.warn("There is no need for processChallenge, not even one result for challenge id:" + challenge.getChallengeId());
            return false;
        }
        if (results.size() == 1) {
            return false;
        }
        if (results.size() > 2) {
            throw new ServiceException("Something is wrong there are tooo many results !!!!! Challenge id: "
                    + challenge.getChallengeId());
        }
        return true;
    }

    private void setChallengeResultsState(List<ChallengeResult> results, ChallengeResultState state) {
        results.forEach(challengeResult -> challengeResult.setState(state));
    }

    @Transactional
    public void saveChallengeResult(int userId, ChallengeResultModel challengeResultModel) throws ServiceException {
        User user = userService.getUserById(userId);
        Challenge challenge = challengeDao.findById(challengeResultModel.getChallengeId());
        validateChallenge(challenge, ChallengeState.ACCEPTED, challengeResultModel.getChallengeId());

        Team team = getTeamFromChallenge(user, challenge);

        List<ChallengeResult> challengeResults = new ArrayList<>();
        challengeResults.addAll(challenge.getChallengeResultsByChallengeId());
        if (CustomValidationUtils.isEmpty(challengeResults)) {
            //add first result
            createChallengeResult(challengeResultModel, challenge, team);
        } else {
            //add other team result
            ChallengeResult firstResult = getFirstChallengeResult(challengeResults);
            checkFirstTeamValidity(firstResult, team);
            createChallengeResult(challengeResultModel, challenge, team);
            processChallengeSave(challenge);
        }
    }

    private void createChallengeResult(ChallengeResultModel challengeResultModel, Challenge challenge, Team team) throws ServiceException {
        if (challenge.getChallengeResultsByChallengeId() == null) {
            challenge.setChallengeResultsByChallengeId(new ArrayList<>());
        }
        Team winnerTeam = null;

        if(challengeResultModel.getWinnerTeamId() != 0){
            winnerTeam = getWinnerTeam(challenge, challengeResultModel.getWinnerTeamId());
            challengeResultModel.setDraw(1);
        }

        ChallengeResult result = challengeResultMapper.challengeResultModelToChallengeResult(challengeResultModel, challenge, winnerTeam, team);

        challenge.getChallengeResultsByChallengeId().add(result);
        challengeResultDao.create(result);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateChallengeResult(int userId, ChallengeResultModel challengeResultModel) throws ServiceException {
        User user = userService.getUserById(userId);
        Challenge challenge = challengeDao.findById(challengeResultModel.getChallengeId());
        validateChallenge(challenge, ChallengeState.ACCEPTED, challengeResultModel.getChallengeId());

        Team team = getTeamFromChallenge(user, challenge);
        ChallengeResult teamResult = getChallengeResultForTeam(challenge, team);
        Team winner = teamDao.findById(challengeResultModel.getWinnerTeamId());

        teamResult.setDraw(challengeResultModel.getDraw());
        teamResult.setTeamByWinnerTeamId(winner);
//        teamResult.setScoreChallenger(challengeResultModel.getScoreChallenger());
//        teamResult.setScoreOpponent(challengeResultModel.getScoreOpponent());

        challengeResultDao.update(teamResult);
        processChallengeSave(challenge);
    }

    private ChallengeResult getChallengeResultForTeam(Challenge challenge, Team team) throws ServiceException {
        for (ChallengeResult result : challenge.getChallengeResultsByChallengeId()) {
            if (result.getCreator().getTeamId() == team.getTeamId()) {
                return result;
            }
        }
        throw new ServiceException(String.format("Team id: %s doesnt have ChallengeResult for Challenge id: %s",
                team.getTeamId(), challenge.getChallengeId()));
    }

    private ChallengeResult getFirstChallengeResult(List<ChallengeResult> challengeResult) throws ServiceException {
        if (challengeResult.size() > 1) {
            throw new ServiceException("Trying to add more than 2 challenge results for ChallengeResult id: "
                    + challengeResult.get(0).getChallengeByChallengesChallengeId().getChallengeId());
        }
        return challengeResult.get(0);
    }

    private boolean checkResultValidity(List<ChallengeResult> results) throws ServiceException {
        return results.get(0).getTeamByWinnerTeamId().getTeamId() == results.get(1).getTeamByWinnerTeamId().getTeamId()
                || (results.get(0).getDraw() == 1 && results.get(1).getDraw() == 1);
    }

    private boolean checkScoreValidity(List<ChallengeResult> results) throws ServiceException {
        return results.get(0).getScoreChallenger() == results.get(1).getScoreChallenger()
                && results.get(0).getScoreOpponent() == results.get(1).getScoreOpponent();
    }

    private Team getTeamFromChallenge(User user, Challenge challenge) throws ServiceException {
        Team team = challenge.getTeamByChallengerTeamId();
        if (isUserInTeam(team.getTeamUsersByTeamId(), user)) {
            return team;
        }
        team = challenge.getTeamByOponnentTeamId();
        if (isUserInTeam(team.getTeamUsersByTeamId(), user)) {
            return team;
        }
        throw new ServiceException(String.format("User id: %s is not from challenge id: %s",
                user.getUserId(), challenge.getChallengeId()));
    }

    private void checkFirstTeamValidity(ChallengeResult firstResult, Team team) throws ServiceException {
        if (firstResult.getCreator().getTeamId() == team.getTeamId()) {
            throw new ServiceException(String.format("Team id: %s already set ChallengeResult id: %s ",
                    team.getTeamId(), firstResult.getChallengeResultId()));
        }
    }

    private Team getWinnerTeam(Challenge challenge, int winnerTeamId) throws ServiceException {
        if (challenge.getTeamByChallengerTeamId().getTeamId() == winnerTeamId) {
            return challenge.getTeamByChallengerTeamId();
        }
        if (challenge.getTeamByOponnentTeamId() != null && challenge.getTeamByOponnentTeamId().getTeamId() == winnerTeamId) {
            return challenge.getTeamByOponnentTeamId();
        }
        throw new ServiceException("Cant find winner team for challenge id: " + challenge.getChallengeId());
    }


}
