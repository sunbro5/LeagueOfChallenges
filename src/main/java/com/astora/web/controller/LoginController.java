package com.astora.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author <a href="mailto:mares.jan@o2.cz">Jan Mares</a>, 16.10.2017
 */
@Controller
public class LoginController {

    @RequestMapping("/login")
    public ModelAndView loginUser(ModelAndView model){
        model.setViewName("login");
        return model;
    }

    @RequestMapping("/denied")
    public ModelAndView loginDenied(ModelAndView model){
        model.setViewName("denied");
        return model;
    }

}
