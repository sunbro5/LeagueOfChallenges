package com.astora.web.controller;

import com.astora.web.service.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class MapController extends BaseUserPage{

    private ChallengeService challengeService;

    @Autowired
    public void setChallengeService(ChallengeService challengeService) {
        this.challengeService = challengeService;
    }


    public ModelAndView renderMap(Map<String, Object> map) {
        map.put("challenges", challengeService.getAllActiveChallenges());
        return new ModelAndView("map", map);
    }

    @RequestMapping("/showMap")
    public ModelAndView showMap(){
        return renderMap(init());
    }

}
