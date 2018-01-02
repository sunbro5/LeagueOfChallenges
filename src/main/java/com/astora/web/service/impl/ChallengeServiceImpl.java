package com.astora.web.service.impl;

import com.astora.web.dao.ChallengeDao;
import com.astora.web.dao.model.Challenge;
import com.astora.web.dao.model.Game;
import com.astora.web.dao.model.Team;
import com.astora.web.dto.ChallengeDto;
import com.astora.web.exception.ServiceException;
import com.astora.web.model.CreateChallengeModel;
import com.astora.web.service.ChallengeService;
import com.astora.web.service.TeamService;
import com.astora.web.utils.CustomValidationUtils;
import com.astora.web.utils.DateUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("challengeService")
public class ChallengeServiceImpl implements ChallengeService {

    private static final Logger logger = Logger.getLogger(ChallengeServiceImpl.class);

    private ChallengeDao challengeDao;
    private TeamService teamService;

    @Autowired
    public void setRoleDao(ChallengeDao challengeDao) {
        this.challengeDao = challengeDao;
    }

    @Autowired
    public void setTeamService(TeamService teamService) {
        this.teamService = teamService;
    }

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
                challengeDto.setOponnentTeamId(challenge.getTeamByOponnentTeamId().getTeamId());
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
        if(CustomValidationUtils.isEmpty(createChallengeModel.getCoordsLat()) || CustomValidationUtils.isEmpty(createChallengeModel.getCoordsLng())){
            throw new ServiceException("Challenge coords are empty. Something is wrong !!!!");
        }
        Team team = teamService.getTeamById(createChallengeModel.getChallengerTeamId());
        Challenge challenge = new Challenge();
        challenge.setText(createChallengeModel.getText());
        challenge.setCoordsLat(createChallengeModel.getCoordsLat());
        challenge.setCoordsLng(createChallengeModel.getCoordsLng());
        challenge.setTeamByChallengerTeamId(team);
        challenge.setChallengeStart(DateUtil.convertToTimestamp(createChallengeModel.getChallengeStart()));
        challenge.setChallengeEnd(DateUtil.convertToTimestamp(createChallengeModel.getChallengeEnd()));
        challengeDao.create(challenge);
    }




}
