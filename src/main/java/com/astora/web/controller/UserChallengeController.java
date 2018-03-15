package com.astora.web.controller;

import com.astora.web.exception.ServiceException;
import com.astora.web.service.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author <a href="mailto:maresjan694@gmail.com">Jan Mares</a>, 16.11.2017
 */
@Controller
@RequestMapping("/user")
public class UserChallengeController extends BaseUserPage {

    @Autowired
    private ChallengeService challengeService;

    @RequestMapping("/userChallenges")
    public ModelAndView showUserChallenges() throws ServiceException {
        return renderUserChallenges();
    }

    private ModelAndView renderUserChallenges() throws ServiceException {
        return renderUserChallenges(init());
    }

    private ModelAndView renderUserChallenges(Map<String, Object> model) throws ServiceException {
        model.put("allActiveChallengesList", challengeService.getUserChallenges(getUserId()));
        model.put("allActiveJoinedChallengesList", challengeService.getOpponentChallenges(getUserId()));
        return new ModelAndView("userChallenges", model);
    }

    @RequestMapping("/cancelChallenge")
    public ModelAndView cancelChallenge(@RequestParam("challengeId") int challengeId) throws ServiceException {
        challengeService.cancelChallenge(getUserId(), challengeId);
        Map<String, Object> model = init();
        pushInfo(model, "userChallenge.canceled");
        return renderUserChallenges(model);
    }

}
