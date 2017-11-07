package com.astora.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author <a href="mailto:mares.jan@o2.cz">Jan Mares</a>, 6.11.2017
 */
@Controller
public class MessageController {

    @RequestMapping("/messages")
    public ModelAndView showMessages(Map<String, Object> model){
        return new ModelAndView("messages",model);
    }
}
