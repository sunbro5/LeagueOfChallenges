package com.astora.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author <a href="mailto:maresjan694@gmail.com">Jan Mares</a>, 16.11.2017
 */
@Controller
public class UserChallengeController extends BaseUserPage {

    @RequestMapping("/userChallenges")
    public ModelAndView showUserChallenges(){
        return renderUserChallenges();
    }

    private ModelAndView renderUserChallenges(){
        return renderUserChallenges(init());
    }

    private ModelAndView renderUserChallenges(Map<String, Object> model){
        return new ModelAndView("userChallenges",model);
    }

    @RequestMapping("/cancelChallenge")
    public ModelAndView cancelChallenge(){

        return renderUserChallenges();
    }

}
