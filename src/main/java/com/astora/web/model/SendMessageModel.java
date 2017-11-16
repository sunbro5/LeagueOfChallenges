package com.astora.web.model;

/**
 * @author <a href="mailto:maresjan694@gmail.com">Jan Mares</a>, 9.11.2017
 */
public class SendMessageModel {

    public static final String MODEL_NAME = "sendMessageModel";

    private String subject = "BASIC";
    private String text;
    private String toNickname;

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

    public String getToNickname() {
        return toNickname;
    }

    public void setToNickname(String toNickname) {
        this.toNickname = toNickname;
    }
}
