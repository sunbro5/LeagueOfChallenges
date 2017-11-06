package com.astora.web.controller;

import com.astora.web.model.CreateChallengeModel;
import com.astora.web.service.ChallengeService;
import com.astora.web.service.GameService;
import com.astora.web.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Controller
public class MapController {

    private ChallengeService challengeService;

    private GameService gameService;

    private TeamService teamService;

    @Autowired
    public void setChallengeService(ChallengeService challengeService) {
        this.challengeService = challengeService;
    }

    @Autowired
    public void setGameService(GameService gameService) {
        this.gameService = gameService;
    }

    @Autowired
    public void setTeamService(TeamService teamService) {
        this.teamService = teamService;
    }

    @ModelAttribute("createChallengeModel")
    CreateChallengeModel getCreateChallengeModel() {
        return new CreateChallengeModel();
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd. MM. yyyy, HH:mm");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
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

    @RequestMapping(value = "/createChallenge")
    public ModelAndView createChallenge(Map<String, Object> map) {
        return new ModelAndView("createChallenge", map);
    }

    @RequestMapping(value = "/createChallengeForm", method = RequestMethod.POST)
    public ModelAndView createChallengeForm(@ModelAttribute("createChallengeModel") CreateChallengeModel createChallengeModel, Map<String, Object> map) {
        //createChallengeModel.setChallengerTeam(teamService.findById(1)); // in the future will be set automaticly from session team
        challengeService.create(createChallengeModel);

        map.put("challenges", challengeService.prepareChallenges());
        return new ModelAndView("map", map);
    }
}
