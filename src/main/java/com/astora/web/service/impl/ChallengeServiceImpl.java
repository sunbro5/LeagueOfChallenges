package com.astora.web.service.impl;

import com.astora.web.dao.ChallengeDao;
import com.astora.web.dao.model.Challenge;
import com.astora.web.dao.model.Game;
import com.astora.web.dto.ChallengeDto;
import com.astora.web.model.CreateChallengeModel;
import com.astora.web.service.ChallengeService;
import com.astora.web.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service("challengeService")
public class ChallengeServiceImpl implements ChallengeService {

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

    @Transactional()
    public void create(Challenge challenge) {
        challengeDao.create(challenge);
    }

    @Transactional
    public void create(CreateChallengeModel createChallengeModel) {
        Challenge challenge = new Challenge();
        challenge.setText(createChallengeModel.getText());
        challenge.setTeamByChallengerTeamId(teamService.findById(1));// will be get from session

        challenge.setChallengeStart(new Timestamp(createChallengeModel.getChallengeStart().getTime()));
        challenge.setChallengeEnd(new Timestamp(createChallengeModel.getChallengeEnd().getTime()));
        challenge.setCoordsLat(createChallengeModel.getCoordsLat());
        challenge.setCoordsLng(createChallengeModel.getCoordsLng());
        challengeDao.create(challenge);
    }

    @Transactional
    public void update(Challenge challenge) {
        challengeDao.update(challenge);
    }

    @Transactional
    public Challenge findById(int id) {
        return challengeDao.findById(id);
    }

    @Transactional
    public List<Challenge> findAll() {
        return challengeDao.findAll();
    }

    @Transactional
    public void delete(int id) {
        challengeDao.delete(id);
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


}
