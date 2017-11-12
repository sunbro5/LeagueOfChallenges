package com.astora.web.controller;

import com.astora.web.model.CreateChallengeModel;
import com.astora.web.service.ChallengeService;
import com.astora.web.service.TeamUserService;
import com.astora.web.session.UserSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Controller
public class CreateChallengeController {

    private ChallengeService challengeService;
    private TeamUserService teamUserService;
    private UserSessionManager userSessionManager;

    @Autowired
    public void setChallengeService(ChallengeService challengeService) {
        this.challengeService = challengeService;
    }

    @Autowired
    public void setTeamUserService(TeamUserService teamUserService) {
        this.teamUserService = teamUserService;
    }

    @Autowired
    public void setUserSessionManager(UserSessionManager userSessionManager) {
        this.userSessionManager = userSessionManager;
    }

    @ModelAttribute("createChallengeModel")
    public CreateChallengeModel getCreateChallengeModel() {
        return new CreateChallengeModel();
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }

    @RequestMapping(value = "/createChallenge")
    public ModelAndView createChallenge(Map<String, Object> map, HttpServletResponse response) throws IOException {
        if(userSessionManager.getUserId() == 0){
            response.sendRedirect("/login");
        }
        map.put("userTeams", teamUserService.getAllTeamsForUser(userSessionManager.getUserId()));

        return new ModelAndView("createChallenge", map);
    }

    @RequestMapping(value = "/chooseCoords")
    public ModelAndView chooseCoords(Map<String, Object> map) {
        return new ModelAndView("chooseCoords", map);
    }

    @RequestMapping(value = "/createChallengeForm", method = RequestMethod.POST)
    public ModelAndView createChallengeForm(@ModelAttribute("createChallengeModel") CreateChallengeModel createChallengeModel, Map<String, Object> map) {
        challengeService.create(createChallengeModel);

        map.put("challenges", challengeService.prepareChallenges());
        return new ModelAndView("map", map);
    }
}
