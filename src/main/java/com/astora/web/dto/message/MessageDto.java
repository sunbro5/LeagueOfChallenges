package com.astora.web.dto.message;

import java.util.Date;

/**
 * @author <a href="mailto:mares.jan@o2.cz">Jan Mares</a>, 9.11.2017
 */
public class MessageDto {

    boolean received;
    private String text;
    private Date sentDate;

    public MessageDto(boolean received, String text, Date sentDate) {
        this.received = received;
        this.text = text;
        this.sentDate = sentDate;
    }

    public boolean isReceived() {
        return received;
    }

    public void setReceived(boolean received) {
        this.received = received;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getSentDate() {
        return sentDate;
    }

    public void setSentDate(Date sentDate) {
        this.sentDate = sentDate;
    }
}
