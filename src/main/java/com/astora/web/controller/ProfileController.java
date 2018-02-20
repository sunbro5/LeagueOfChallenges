package com.astora.web.controller;

import com.astora.web.exception.ServiceException;
import com.astora.web.exception.UserDoesntExists;
import com.astora.web.service.UserService;
import com.astora.web.utils.CustomValidationUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author <a href="mailto:maresjan694@gmail.com">Jan Mares</a>, 21.11.2017
 */
@Controller
@RequestMapping("/user")
public class ProfileController extends BaseUserPage {

    private static final Logger logger = Logger.getLogger(ProfileController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/userProfile")
    public ModelAndView showProfile(@RequestParam(value = "userNickname", required = false) String nickname) throws ServiceException {
        return renderProfile(init(), nickname);
    }

    private ModelAndView renderProfile(Map<String, Object> model, String nickname) throws ServiceException {
        if (CustomValidationUtils.isEmpty(nickname)) {
            nickname = userService.getUserById(getUserId()).getNickname();
        }
        try {
            model.put("userByNicknameInfo",userService.getUserInfoByNickname(nickname));
        } catch (UserDoesntExists userDoesntExists) {
            pushInfo(model, "message.report.userDoesntExists");
        }
        return new ModelAndView("userProfile", model);
    }

}
