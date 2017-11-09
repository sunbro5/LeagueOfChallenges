package com.astora.web.dto.message;

import com.astora.web.dao.model.Message;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author <a href="mailto:mares.jan@o2.cz">Jan Mares</a>, 9.11.2017
 */
public class UserMessagesDto implements Serializable {

    private String textPreview;
    private String toNickname;
    private List<MessageDto> messages;

    public UserMessagesDto(String toNickname) {
        this.toNickname = toNickname;
    }

    public String getTextPreview() {
        return textPreview;
    }

    public void setTextPreview(String text) {
        this.textPreview = text;
    }

    public String getToNickname() {
        return toNickname;
    }

    public void setToNickname(String toNickname) {
        this.toNickname = toNickname;
    }

    public List<MessageDto> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageDto> messages) {
        this.messages = messages;
    }
}
