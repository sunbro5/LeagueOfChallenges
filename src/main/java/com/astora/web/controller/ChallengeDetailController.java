package com.astora.web.controller;

import com.astora.web.dto.challenge.ChallengeInfoDto;
import com.astora.web.exception.ServiceException;
import com.astora.web.exception.UserConflictException;
import com.astora.web.model.ChallengeResultModel;
import com.astora.web.service.ChallengeService;
import com.astora.web.service.GameService;
import com.astora.web.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class ChallengeDetailController extends BaseUserPage {

    @Autowired
    private ChallengeService challengeService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private GameService gameService;

    @RequestMapping("/challengeDetail")
    public ModelAndView challengeDetail(@RequestParam("challengeId") int challengeId, Map<String, Object> map) throws ServiceException {
        return renderChallengeDetail(challengeId, init());
    }

    @RequestMapping("/joinChallenge")
    public ModelAndView joinChallenge(@RequestParam("challengeId") int challengeId, @RequestParam("teamId") int teamId) throws ServiceException {
        Map<String, Object> model = init();
        try {
            challengeService.joinChallenge(getUserId(), teamId, challengeId);
        } catch (UserConflictException e) {
            pushInfo(model, "joinChallenge.userTeamConflict");
            return renderChallengeDetail(challengeId, model);
        }
        pushInfo(model, "joinChallenge.successfully");
        return renderChallengeDetail(challengeId, model);
    }

    @RequestMapping("/acceptChallenge")
    public ModelAndView acceptChallenge(@RequestParam("challengeId") int challengeId) throws ServiceException {
        Map<String, Object> model = init();
        challengeService.acceptChallenge(getUserId(), challengeId);
        pushInfo(model, "joinChallenge.successfully");
        return renderChallengeDetail(challengeId, model);
    }

    @RequestMapping("/declineChallenge")
    public ModelAndView declineChallenge(@RequestParam("challengeId") int challengeId) throws ServiceException {
        Map<String, Object> model = init();
        challengeService.declineChallenge(getUserId(), challengeId);
        pushInfo(model, "joinChallenge.successfully");
        return renderChallengeDetail(challengeId, model);
    }

    public ModelAndView renderChallengeDetail(int challengeId, Map<String, Object> map) throws ServiceException {
        int userId = getUserId();
        ChallengeInfoDto challengeInfoDto = challengeService.getChallengeDetail(challengeId);
        map.put("challengeDetail", challengeInfoDto);
        boolean isUserChallenge = challengeService.isUserChallenge(userId, challengeId);
        if (!isUserChallenge) {
            map.put("isOpponentChallenge", challengeService.isOpponentChallenge(userId, challengeId));
            map.put("userTeamInformationList", teamService.getTeamsByGameName(userId, challengeInfoDto.getGameName()));
        }
        map.put("isUserChallenge", isUserChallenge);
        map.put("canUserSetResult", challengeService.canUserSetResult(userId, challengeId));
        return new ModelAndView("challengeDetail", map);
    }

    @RequestMapping("/challengeResult")
    public ModelAndView showChallengeResult(@RequestParam("challengeId") int challengeId) throws ServiceException {
        Map<String, Object> map = init();
        ChallengeResultModel challengeResultModel = new ChallengeResultModel();
        challengeResultModel.setChallengeId(challengeId);
        map.put(ChallengeResultModel.MODEL_NAME, challengeResultModel);
        return renderChallengeResult(map, challengeId);
    }

    public ModelAndView renderChallengeResult(Map<String, Object> map, int challengeId) throws ServiceException {
        boolean isUserChallenge = challengeService.isUserChallenge(getUserId(), challengeId);
        if (!isUserChallenge) {
            map.put("isOpponentChallenge", challengeService.isOpponentChallenge(getUserId(), challengeId));
        }
        map.put("isUserChallenge", isUserChallenge);
        ChallengeInfoDto challengeInfoDto = challengeService.getChallengeDetail(challengeId);
        map.put("challengeDetail", challengeInfoDto);
        return new ModelAndView("challengeResult", map);
    }

    @RequestMapping("/creteChallengeResult")
    public ModelAndView createResult(@ModelAttribute(ChallengeResultModel.MODEL_NAME) @Valid ChallengeResultModel model, BindingResult result) throws ServiceException {
        if(result.hasErrors()){
            return renderChallengeResult(init(), model.getChallengeId());
        }
        challengeService.saveChallengeResult(getUserId(),model);
        return renderChallengeDetail(model.getChallengeId(),init());
    }


}
