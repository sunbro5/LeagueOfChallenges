package com.astora.web.controller;

import com.astora.web.cache.GameCache;
import com.astora.web.exception.ServiceException;
import com.astora.web.model.CreateChallengeModel;
import com.astora.web.model.validator.CreateChallengeModelValidator;
import com.astora.web.service.ChallengeService;
import com.astora.web.service.GameService;
import com.astora.web.service.TeamService;
import com.astora.web.service.TeamUserService;
import com.astora.web.session.UserSessionManager;
import org.apache.log4j.Logger;
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
public class CreateChallengeController extends BaseUserPage {

    private static final Logger logger = Logger.getLogger(CreateChallengeController.class);

    @Autowired
    private GameService gameService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private CreateChallengeModelValidator createChallengeModelValidator;

    private ChallengeService challengeService;
    private TeamUserService teamUserService;


    @Autowired
    public void setChallengeService(ChallengeService challengeService) {
        this.challengeService = challengeService;
    }

    @Autowired
    public void setTeamUserService(TeamUserService teamUserService) {
        this.teamUserService = teamUserService;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(createChallengeModelValidator);
    }

    @ModelAttribute("createChallengeModel")
    public CreateChallengeModel getCreateChallengeModel() {
        return new CreateChallengeModel();
    }

    private ModelAndView renderCreateChallenge(Map<String, Object> model) {
        model.put("gameTypes", gameService.getAllGameNames());
        model.put("userTeamsList",teamService.getAllUserTeams(getUserId()));
        return new ModelAndView("createChallenge", model);
    }

    @RequestMapping(value = "/createChallenge")
    public ModelAndView createChallenge(HttpServletResponse response) throws IOException {
        return renderCreateChallenge(init());
    }


    @RequestMapping(value = "/createChallengeForm", method = RequestMethod.POST)
    public ModelAndView createChallengeForm(@ModelAttribute("createChallengeModel") CreateChallengeModel createChallengeModel) {
        try {
            challengeService.createChallenge(createChallengeModel);
        } catch (ServiceException e) {
            logger.error(e);
            renderCreateChallenge(init());
        }
        userSessionManager.putUserInfo("createChallenge.successfully.created");
        return renderCreateChallenge(init());
    }
}
