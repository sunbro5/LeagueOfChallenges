package com.astora.web.controller;

import com.astora.web.dto.UserNotificationsDto;
import com.astora.web.dto.message.UserMessagesDto;
import com.astora.web.exception.ServiceException;
import com.astora.web.service.MessageService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="mailto:mares.jan@o2.cz">Jan Mares</a>, 30.11.2017
 */
@RestController
public class UserNotificationRestController extends BaseUserPage {

    private static final Logger logger = Logger.getLogger(UserNotificationRestController.class);

    @Autowired
    private MessageService messageService;

    @RequestMapping("/refreshMessageNotifications")
    public @ResponseBody
    UserNotificationsDto getMessageNotifications() {
        UserNotificationsDto notification = new UserNotificationsDto();
        notification.setUserMessages(messageService.getNotificationMessagesPreview(getUserId()));
        return notification;
    }

    @RequestMapping("/testRest")
    public @ResponseBody
    UserMessagesDto test() {
        UserMessagesDto message = new UserMessagesDto("test");
        return message;
    }
}
