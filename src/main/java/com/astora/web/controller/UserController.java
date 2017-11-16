package com.astora.web.controller;

import com.astora.web.dto.FriendInfoDto;
import com.astora.web.exception.FriendAlreadyExistsException;
import com.astora.web.exception.ServiceException;
import com.astora.web.service.FriendService;
import com.astora.web.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:maresjan694@gmail.com">Jan Mares</a>, 06.11.2017
 */
@Controller
public class UserController extends BaseUserPage {

    private static final Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private FriendService friendService;

    @Autowired
    private UserService userService;

    @RequestMapping("/friends")
    public ModelAndView showFriends(){
        return renderFriends(init());
    }

    public ModelAndView renderFriends(Map<String, Object> model){

        List<FriendInfoDto> list = null;
        try {
            list = friendService.getFriendList(getUserId());
        } catch (ServiceException e) {
            logger.error(e);
        }
        model.put("userFriendList",list);
        return new ModelAndView("friends",model);
    }

    @RequestMapping("/findFriendUser")
    public ModelAndView findFriendUser(@RequestParam("nickname") String nickname){
        Map<String, Object> model = init();
        List<String> foundUsers = userService.getUsersNicknameLike(nickname);
        model.put("foundUsersList",foundUsers);
        return renderFriends(model);
    }

    @RequestMapping("/createFriend")
    public ModelAndView createFriend(@RequestParam("nickname") String nickname){
        try {
            friendService.createFriend(getUserId(),nickname);
        } catch (FriendAlreadyExistsException e) {
            userSessionManager.putUserInfo("message.friend.alreadyExists");
            return renderFriends(init());
        } catch (ServiceException e) {
            logger.error(e);
            return renderFriends(init());
        }
        userSessionManager.putUserInfo("message.friend.successfullyCreated");
        return renderFriends(init());
    }

    @RequestMapping("/deleteFriend")
    public ModelAndView deleteFriend(@RequestParam("nickname") String nickname){
        try {
            if(friendService.removeFriendByNickname(getUserId(),nickname)){
                userSessionManager.putUserInfo("message.friend.successfullyRemoved");
            }
        } catch (ServiceException e) {
            logger.error(e);
        }
        return renderFriends(init());
    }

}
