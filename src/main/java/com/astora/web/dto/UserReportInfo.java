package com.astora.web.dto;

import com.astora.web.enums.ReportReason;

import java.io.Serializable;

/**
 * @author <a href="mailto:maresjan694@gmail.com">Jan Mares</a>, 6.12.2017
 */
public class UserReportInfo {

    private String nickname;
    private ReportReason reason;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public ReportReason getReason() {
        return reason;
    }

    public void setReason(ReportReason reason) {
        this.reason = reason;
    }
}
