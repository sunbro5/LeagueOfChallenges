package com.astora.web.service.impl;

import com.astora.web.dao.ChallengeDao;
import com.astora.web.dao.model.*;
import com.astora.web.dto.ChallengeDto;
import com.astora.web.dto.ChallengeInfoDto;
import com.astora.web.enums.ChallengeState;
import com.astora.web.exception.ServiceException;
import com.astora.web.exception.UserConflictException;
import com.astora.web.mapper.ChallengeMapper;
import com.astora.web.model.CreateChallengeModel;
import com.astora.web.service.ChallengeService;
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

    @Autowired
    private ChallengeDao challengeDao;

    @Autowired
    private TeamService teamService;

    @Autowired
    private UserService userService;

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
        if (CustomValidationUtils.isEmpty(createChallengeModel.getCoordsLat()) || CustomValidationUtils.isEmpty(createChallengeModel.getCoordsLng())) {
            throw new ServiceException("Challenge coords are empty. Something is wrong !!!!");
        }
        Team team = teamService.getTeamById(createChallengeModel.getChallengerTeamId());
        Challenge challenge = new Challenge();
        challenge.setState(ChallengeState.CREATED.name());
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
        challengeInfoDto.setOpponents(mapTeamUsersToNicknames(challenge.getTeamByOponnentTeamId().getTeamUsersByTeamId()));
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
        if (teamService.isUserInTeam(user, team)) {
            throw new ServiceException(String.format("User nickname: %s is not in team teamName: %s", user.getNickname(), team.getName()));
        }
        Challenge challenge = challengeDao.findById(challengeId);
        validateChallenge(challenge, ChallengeState.CREATED, challengeId);
        validateOpponent(challenge,team);
        challenge.setTeamByOponnentTeamId(team);
        challenge.setState(ChallengeState.CHALLENGED.name());
        challengeDao.update(challenge);
    }

    @Transactional
    public void acceptChallenge(int userId, int challengeId) throws ServiceException {
        setChallengeState(userId, challengeId, ChallengeState.CHALLENGED, ChallengeState.ACCEPTED);
    }

    @Transactional
    public void declineChallenge(int userId, int challengeId) throws ServiceException {
        setChallengeState(userId,challengeId,ChallengeState.CHALLENGED,ChallengeState.CREATED);
    }

    private void setChallengeState(int userId, int challengeId, ChallengeState oldState, ChallengeState newState) throws ServiceException {
        User user = userService.getUserById(userId);
        Challenge challenge = challengeDao.findById(challengeId);
        validateChallenge(challenge, oldState, challengeId);
        validateTeamUser(challenge,user,true);
        challenge.setState(newState.name());
        challengeDao.update(challenge);
    }

    private void validateChallenge(Challenge challenge, ChallengeState challengeState, int challengeId) throws ServiceException {
        if (challenge == null || !challenge.getState().equals(challengeState.name())) {
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
        for (TeamUser userOpponent: team.getTeamUsersByTeamId()){
            for (TeamUser userChallenger: challenge.getTeamByChallengerTeamId().getTeamUsersByTeamId()){
                if(userOpponent.getUserByUserUserId().getUserId() == userChallenger.getUserByUserUserId().getUserId()){
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

    private List<String> mapTeamUsersToNicknames(Collection<TeamUser> list){
        return list.stream().map(TeamUser::getUserByUserUserId).map(User::getNickname).collect(Collectors.toList());
    }
}
