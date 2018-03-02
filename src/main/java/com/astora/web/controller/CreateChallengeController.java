package com.astora.web.controller;

import com.astora.web.exception.ServiceException;
import com.astora.web.model.CreateChallengeModel;
import com.astora.web.model.validator.CreateChallengeModelValidator;
import com.astora.web.service.ChallengeService;
import com.astora.web.service.GameService;
import com.astora.web.service.TeamService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class CreateChallengeController extends BaseUserPage {

    @Autowired
    private GameService gameService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private CreateChallengeModelValidator createChallengeModelValidator;

    @Autowired
    private ChallengeService challengeService;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(createChallengeModelValidator);
    }

    @ModelAttribute("createChallengeModel")
    public CreateChallengeModel getCreateChallengeModel() {
        return new CreateChallengeModel();
    }

    private ModelAndView renderCreateChallenge(Map<String, Object> model) throws ServiceException {
        model.put("gameTypes", gameService.getAllGameNames());
        model.put("userTeamsList",teamService.getAllUserTeams(getUserId()));
        return new ModelAndView("createChallenge", model);
    }

    @RequestMapping(value = "/createChallenge")
    public ModelAndView createChallenge() throws ServiceException {
        return renderCreateChallenge(init());
    }


    @RequestMapping(value = "/createChallengeForm", method = RequestMethod.POST)
    public ModelAndView createChallengeForm(@ModelAttribute("createChallengeModel") @Validated CreateChallengeModel createChallengeModel, BindingResult result) throws ServiceException {
        Map<String, Object> model = init();
        if(result.hasErrors()){
            return renderCreateChallenge(model);
        }
        challengeService.createChallenge(createChallengeModel);
        pushInfo(model, "createChallenge.successfully.created");
        return renderCreateChallenge(model);
    }
}
