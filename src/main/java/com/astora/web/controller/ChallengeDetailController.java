package com.astora.web.controller;

import com.astora.web.exception.ServiceException;
import com.astora.web.exception.UserConflictException;
import com.astora.web.service.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/user")
public class ChallengeDetailController extends BaseUserPage {

    @Autowired
    private ChallengeService challengeService;

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
        challengeService.acceptChallenge(getUserId(),challengeId);
        pushInfo(model, "joinChallenge.successfully");
        return renderChallengeDetail(challengeId,model);
    }

    @RequestMapping("/declineChallenge")
    public ModelAndView declineChallenge(@RequestParam("challengeId") int challengeId) throws ServiceException {
        Map<String, Object> model = init();
        challengeService.declineChallenge(getUserId(), challengeId);
        pushInfo(model, "joinChallenge.successfully");
        return renderChallengeDetail(challengeId,model);
    }

    public ModelAndView renderChallengeDetail(int challengeId, Map<String, Object> model) throws ServiceException {
        model.put("challengeDetail", challengeService.getChallengeDetail(challengeId));
        return new ModelAndView("challengeDetail", model);
    }

}
