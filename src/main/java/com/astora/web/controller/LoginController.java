package com.astora.web.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author <a href="mailto:maresjan694@gmail.com">Jan Mares</a>, 16.10.2017
 */
@Controller
public class LoginController extends BaseUserPage{

    @RequestMapping("/login")
    public ModelAndView loginUser(){
        return renderLogin(init());
    }

    public ModelAndView renderLogin(Map<String,Object> model){
        return new ModelAndView("login",model);
    }

    @RequestMapping("/denied")
    public ModelAndView loginDenied(Map<String,Object> model){
        userSessionManager.putUserInfo("message.login.denied");
        return renderLogin(init());
    }

    @RequestMapping("/logout")
    public ModelAndView logout(Map<String,Object> model){
        userSessionManager.putUserInfo("message.login.successful");
        return renderLogin(model);
    }

}
