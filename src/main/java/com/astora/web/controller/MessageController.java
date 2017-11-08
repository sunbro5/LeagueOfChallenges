package com.astora.web.controller;

import com.astora.web.dto.UserInboxMessageDto;
import com.astora.web.exception.ServiceException;
import com.astora.web.service.UserService;
import com.astora.web.utils.CustomValidationUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:mares.jan@o2.cz">Jan Mares</a>, 6.11.2017
 */
@Controller
public class MessageController extends BaseUserPage {

    private static final Logger logger = Logger.getLogger(MessageController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/messages")
    public ModelAndView showMessages() {
        return renderMessages(init());
    }

    public ModelAndView renderMessages(Map<String, Object> model) {
        List<UserInboxMessageDto> messages = null;
        try {
            messages = userService.getUserMessages(getUserId());
        } catch (ServiceException e) {
            logger.error(e);
        }
        if(!CustomValidationUtils.isEmpty(messages)){
            model.put("userInboxMessageList",messages);
        }
        return new ModelAndView("messages", model);
    }
}
