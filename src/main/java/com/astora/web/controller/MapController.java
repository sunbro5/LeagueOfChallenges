package com.astora.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class MapController {

    @RequestMapping("/showMap")
    public ModelAndView showMap(Map<String, Object> map)     {
        return new ModelAndView("map", map);
    }
}
