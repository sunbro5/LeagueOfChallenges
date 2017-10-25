package com.astora.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author <a href="mailto:mares.jan@o2.cz">Jan Mares</a>, 16.10.2017
 */
@Controller
public class LoginController {

    @RequestMapping("/login")
    public ModelAndView loginUser(Map<String,Object> model){
        return renderLogin(model);
    }

    public ModelAndView renderLogin(Map<String,Object> model){
        return new ModelAndView("login",model);
    }

    @RequestMapping("/denied")
    public ModelAndView loginDenied(Map<String,Object> model){
        model.put("accessDenied", true);
        return renderLogin(model);
    }

}
