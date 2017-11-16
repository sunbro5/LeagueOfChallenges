package com.astora.web.model;

/**
 * @author <a href="mailto:mares.jan@o2.cz">Jan Mares</a>, 16.11.2017
 */
public class UserReportModel {

    public static final String MODEL_NAME = "userReportModel";

    private String reason;
    private String reasonText;
    private String nickname;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getReasonText() {
        return reasonText;
    }

    public void setReasonText(String reasonText) {
        this.reasonText = reasonText;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
