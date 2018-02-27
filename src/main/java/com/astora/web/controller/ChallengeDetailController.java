package com.astora.web.controller;

import com.astora.web.service.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/user")
public class ChallengeDetailController {

    @Autowired
    private ChallengeService challengeService;

    @RequestMapping("/challengeDetail")
    public ModelAndView challengeDetail(@RequestParam("challengeId") int challengeId, Map<String, Object> map) {
        //map.put(CHALLENGE_MODEL_KEY, challengeService.findById(challengeId));
        return new ModelAndView("challengeDetail", map);
    }

}
