package com.astora.web.controller;

import com.astora.web.utils.HibernateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author <a href="mailto:maresjan694@gmail.com">Jan Mares</a>, 16.10.2017
 */
@Controller
public class HomeController {

    @RequestMapping("/home")
    public ModelAndView printHello(ModelAndView model) throws Exception {
        model.addObject("text","hello");
        model.setViewName("welcome");
        //HibernateUtil.getSessionFactory();
        return model;
    }
}
