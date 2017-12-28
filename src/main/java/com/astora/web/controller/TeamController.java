package com.astora.web.controller;

import com.astora.web.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class TeamController {

    private static final String TEAM_MAPPING = "/team";

    private static final String TEAM_ID_PARAM = "teamId";

    private static final String TEAM_MODEL_KEY = "team";

    private static final String TEAM_VIEW_NAME = "team";

    private TeamService teamService;

    @Autowired
    public void setTeamService(TeamService teamService) {
        this.teamService = teamService;
    }

    @RequestMapping(TEAM_MAPPING)
    public ModelAndView challengeDetail(@RequestParam(TEAM_ID_PARAM) int teamId, Map<String, Object> map) {
        //map.put(TEAM_MODEL_KEY, teamService.findById(teamId));
        return new ModelAndView(TEAM_VIEW_NAME, map);
    }


}
