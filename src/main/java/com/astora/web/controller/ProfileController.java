package com.astora.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author <a href="mailto:mares.jan@o2.cz">Jan Mares</a>, 21.11.2017
 */
@Controller
public class ProfileController extends BaseUserPage{

    @RequestMapping("/userProfile")
    public ModelAndView showProfile(){
        return renderProfile(init());
    }

    private ModelAndView renderProfile(Map<String, Object> model){
        return new ModelAndView("userProfile",model);
    }

}
