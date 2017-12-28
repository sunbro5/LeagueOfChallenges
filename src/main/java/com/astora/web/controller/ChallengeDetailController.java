package com.astora.web.controller;

import com.astora.web.service.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class ChallengeDetailController {

    private static final String CHALLENGE_MAPPING = "/challenge";

    private static final String CHALLENGE_ID_PARAM = "challengeId";

    private static final String CHALLENGE_MODEL_KEY = "challenge";

    private static final String CHALLENGE_VIEW_NAME = "challengeDetail";

    private ChallengeService challengeService;

    @Autowired
    public void setChallengeService(ChallengeService challengeService) {
        this.challengeService = challengeService;
    }

    @RequestMapping(CHALLENGE_MAPPING)
    public ModelAndView challengeDetail(@RequestParam(CHALLENGE_ID_PARAM) int challengeId, Map<String, Object> map) {
        //map.put(CHALLENGE_MODEL_KEY, challengeService.findById(challengeId));
        return new ModelAndView(CHALLENGE_VIEW_NAME, map);
    }

}
