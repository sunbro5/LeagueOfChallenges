package com.astora.web.dto;

import com.astora.web.dto.message.UserMessagesDto;

import java.util.List;

/**
 * @author <a href="mailto:mares.jan@o2.cz">Jan Mares</a>, 30.11.2017
 */
public class UserNotificationsDto {

    private List<UserMessagesDto> userMessages;

    public List<UserMessagesDto> getUserMessages() {
        return userMessages;
    }

    public void setUserMessages(List<UserMessagesDto> userMessages) {
        this.userMessages = userMessages;
    }
}
