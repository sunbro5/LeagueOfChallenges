package com.astora.web.controller;

import com.astora.web.dto.FriendInfo;
import com.astora.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:maresjan694@gmail.com">Jan Mares</a>, 06.11.2017
 */
@Controller
public class UserController extends BaseUserController {

    //Autowired
    private UserService userService;

    @RequestMapping("/friends")
    public ModelAndView showFriends(Authentication authentication){
        return renderFriends(authentication);
    }

    public ModelAndView renderFriends(Authentication authentication){
        Map<String, Object> model = init(authentication);
        int id = getUserId(authentication);
        List<FriendInfo> list = null;
        if(id != 0){
            //list = userService.getFriendList(id);
        }
        //model.put("userFriendList",list);
        return new ModelAndView("friends",model);
    }


}
