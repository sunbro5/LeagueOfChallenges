package com.astora.web.controller;

import com.astora.web.service.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class MapController {

    private ChallengeService challengeService;

    @Autowired
    public void setChallengeService(ChallengeService challengeService) {
        this.challengeService = challengeService;
    }

    @RequestMapping("/showMap")
    public ModelAndView showMap(Map<String, Object> map) {
        map.put("challenges", challengeService.prepareChallenges());
        return new ModelAndView("map", map);
    }

    @RequestMapping("/challenge")
    public ModelAndView challengeDetail(@RequestParam("challengeId") String challengeId, Map<String, Object> map) {
        map.put("challenges", challengeService.prepareChallenges());
        return new ModelAndView("challenge", map);
    }

}
