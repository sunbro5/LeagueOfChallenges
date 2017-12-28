package com.astora.web.controller;

import com.astora.web.exception.ServiceException;
import com.astora.web.exception.UserDoesntExists;
import com.astora.web.model.NewTeamModel;
import com.astora.web.model.validator.NewTeamModelValidator;
import com.astora.web.service.GameService;
import com.astora.web.service.TeamService;
import com.astora.web.utils.CustomValidationUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author <a href="mailto:mares.jan@o2.cz">Jan Mares</a>, 8.12.2017
 */
@Controller
public class UserTeamController extends BaseUserPage {

    private static final Logger logger = Logger.getLogger(UserTeamController.class);

    @Autowired
    private NewTeamModelValidator newTeamModelValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(newTeamModelValidator);
    }

    @Autowired
    private GameService gameService;

    @Autowired
    private TeamService teamService;

    @RequestMapping("/userTeam")
    public ModelAndView showUserTeam(@RequestParam(value = "gameName", required = false) String gameName) {
        return renderUserTeam(init(), gameName);
    }

    private ModelAndView renderUserTeam() {
        return renderUserTeam(init(), null);
    }

    private ModelAndView renderUserTeam(Map<String, Object> model, String gameName) {
        if (!CustomValidationUtils.isEmpty(gameName)) {
            try {
                boolean noTeamGame = gameService.noTeamGame(gameName);
                model.put("isNoTeamGame", noTeamGame);
                if (noTeamGame) {
                    model.put("userNoTeamGameInformation", gameService.getLeagueForNoTeamGame(getUserId(), gameName));
                } else {
                    model.put("userTeamInformationList", teamService.getTeamsByGameName(getUserId(), gameName));
                }
                model.put("gameName", gameName);
            } catch (ServiceException e) {
                logger.error(e);
            }
        }
        model.put("userGamesInformationList", gameService.getGamesInformation());
        return new ModelAndView("userTeam", model);
    }

    @RequestMapping("/createDefaultTeam")
    public ModelAndView createDefaultTeam(@RequestParam(value = "gameName") String gameName) {
        teamService.createDefaultTeam(getUserId(), gameName);
        return renderUserTeam(init(), gameName);
    }

    @RequestMapping("/newTeam")
    public ModelAndView newTeam(@RequestParam("gameName") String gameName) {
        if (CustomValidationUtils.isEmpty(gameName)) {
            renderUserTeam(init(), null);
        }
        try {
            if (gameService.noTeamGame(gameName)) {
                return renderUserTeam();
            }
        } catch (ServiceException e) {
            logger.error(e);
            return renderUserTeam();
        }
        Map<String, Object> model = init();

        model.put("teamUsersCount", gameService.getUsersGameCount(gameName));
        NewTeamModel newTeamModel = new NewTeamModel();
        newTeamModel.setGameName(gameName);
        model.put(NewTeamModel.MODEL_NAME, newTeamModel);
        return renderNewTeam(model);
    }

    private ModelAndView renderNewTeam(Map<String, Object> model) {
        return new ModelAndView("newTeam", model);
    }

    @RequestMapping("/createTeam")
    public ModelAndView createTeam(@ModelAttribute(NewTeamModel.MODEL_NAME) NewTeamModel newTeamModel, BindingResult result) {
        if (result.hasErrors()) {
            return renderUserTeam();
        }
        try {
            teamService.createTeamFromModel(getUserId(), newTeamModel);
        } catch (UserDoesntExists e) {
            logger.error(e);
            userSessionManager.putUserInfo("newTeam.form.error.userName.doesntExist");
            return renderUserTeam();
        }
        userSessionManager.putUserInfo("createTeam.successfully.created");
        return renderUserTeam(init(), newTeamModel.getGameName());
    }

}
