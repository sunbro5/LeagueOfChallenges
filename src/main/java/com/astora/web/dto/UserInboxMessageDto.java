package com.astora.web.dto;

import com.astora.web.dao.model.Message;

import java.util.Date;

/**
 * @author <a href="mmailto:maresjan694@gmail.com</a>, 8.11.2017
 */
public class UserInboxMessageDto {

    private String fromNickname;
    private String subject;
    private String text;
    private Date sendDate;

    public UserInboxMessageDto(Message message) {
        this.fromNickname = message.getUserByFromUserId().getNickname();
        this.subject = message.getSubject();
        this.text = message.getText();
        this.sendDate = message.getCreated();
    }

    public String getFromNickname() {
        return fromNickname;
    }

    public void setFromNickname(String fromNickname) {
        this.fromNickname = fromNickname;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }
}
