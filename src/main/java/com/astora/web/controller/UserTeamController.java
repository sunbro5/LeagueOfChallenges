package com.astora.web.controller;

import com.astora.web.exception.ServiceException;
import com.astora.web.service.GameService;
import com.astora.web.utils.CustomValidationUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    private GameService gameService;

    @RequestMapping("/userTeam")
    public ModelAndView showUserTeam(@RequestParam(value = "gameName", required = false) String gameName) {
        return renderUserTeam(init(), gameName);
    }

    private ModelAndView renderUserTeam(Map<String, Object> model, String gameName) {
        if (!CustomValidationUtils.isEmpty(gameName)) {
            try {
                boolean noTeamGame = gameService.noTeamGame(gameName);
                model.put("isNoTeamGame", noTeamGame);
                if (noTeamGame) {
                    model.put("userNoTeamGameInformation",gameService.getLeagueForNoTeamGame(getUserId(),gameName));
                } else {
                    model.put("userTeamInformationList", gameService.getTeamsByGameName(getUserId(), gameName));
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
        try {
            gameService.createDefaultTeam(getUserId(),gameName);
        } catch (ServiceException e) {
            logger.error(e);
        }
        return renderUserTeam(init(),gameName);
    }
}
