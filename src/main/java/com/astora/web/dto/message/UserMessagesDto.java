package com.astora.web.dto.message;

import java.io.Serializable;
import java.util.Date;

/**
 * @author <a href="mailto:maresjan694@gmail.com">Jan Mares</a>, 9.11.2017
 */
public class UserMessagesDto implements Serializable {

    private String textPreview;
    private String userNickname;
    private Date textPreviewDate;

    public UserMessagesDto(String toNickname) {
        this.userNickname = toNickname;
    }

    public String getTextPreview() {
        return textPreview;
    }

    public void setTextPreview(String text) {
        this.textPreview = text;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public Date getTextPreviewDate() {
        return textPreviewDate;
    }

    public void setTextPreviewDate(Date textPreviewDate) {
        this.textPreviewDate = textPreviewDate;
    }
}
