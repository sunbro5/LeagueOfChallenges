package com.astora.web.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author <a href="mailto:mares.jan@o2.cz">Jan Mares</a>, 16.10.2017
 */
@Controller
public class LoginController {

    @RequestMapping("/login")
    public ModelAndView loginUser(HttpSession session, Map<String,Object> model){
        session.setAttribute("text","tralala");
        return renderLogin(model);
    }

    public ModelAndView renderLogin(Map<String,Object> model){
        return new ModelAndView("login",model);
    }

    @RequestMapping("/denied")
    public ModelAndView loginDenied(Map<String,Object> model){
        model.put("loginMessage", "Access Denied !!");
        return renderLogin(model);
    }

    @RequestMapping("/logout")
    public ModelAndView logout(Map<String,Object> model){
        model.put("loginMessage", "Logout Successful");
        return renderLogin(model);
    }

}
