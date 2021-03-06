package com.astora.web.controller;

import com.astora.web.dto.FriendInfoDto;
import com.astora.web.exception.FriendAlreadyExistsException;
import com.astora.web.exception.ServiceException;
import com.astora.web.exception.UserConflictException;
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
@RequestMapping("/user")
public class FriendController extends BaseUserPage {

    private static final Logger logger = Logger.getLogger(FriendController.class);

    @Autowired
    private FriendService friendService;

    @Autowired
    private UserService userService;

    @RequestMapping("/friends")
    public ModelAndView showFriends() throws ServiceException {
        return renderFriends(init());
    }

    public ModelAndView renderFriends() throws ServiceException {
        return renderFriends(init());
    }

    public ModelAndView renderFriends(Map<String, Object> model) throws ServiceException {
        List<FriendInfoDto> list = friendService.getFriendList(getUserId());
        model.put("userFriendList", list);
        return new ModelAndView("friends", model);
    }

    @RequestMapping("/findFriendUser")
    public ModelAndView findFriendUser(@RequestParam("nickname") String nickname) throws ServiceException {
        Map<String, Object> model = init();
        List<String> foundUsers = userService.getUsersNicknameLike(nickname);
        model.put("foundUsersList", foundUsers);
        return renderFriends(model);
    }

    @RequestMapping("/createFriend")
    public ModelAndView createFriend(@RequestParam("nickname") String nickname) throws ServiceException {
        try {
            friendService.createFriend(getUserId(), nickname);
        } catch (FriendAlreadyExistsException e) {
            userSessionManager.putUserInfo("message.friend.alreadyExists");
            return renderFriends();
        } catch (UserConflictException e) {
            logger.warn(e);
            return renderFriends();
        }
        userSessionManager.putUserInfo("message.friend.successfullyCreated");
        return renderFriends();
    }

    @RequestMapping("/deleteFriend")
    public ModelAndView deleteFriend(@RequestParam("nickname") String nickname) throws ServiceException {
        if (friendService.removeFriendByNickname(getUserId(), nickname)) {
            userSessionManager.putUserInfo("message.friend.successfullyRemoved");
        }
        return renderFriends(init());
    }

}
