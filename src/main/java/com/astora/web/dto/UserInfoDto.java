package com.astora.web.dto;

import com.astora.web.dao.model.User;
import com.astora.web.enums.ReportReason;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * @author <a href="mailto:maresjan694@gmail.com">Jan Mares</a>, 06.11.2017
 */
public class UserInfoDto {

    private String firstName;
    private String nickname;
    private String email;
    private int rating;
    private Date lastLogin;
    private Map<ReportReason,Integer> reportList;

    public UserInfoDto(User user) {
        this.firstName = user.getFirstName();
        this.nickname = user.getNickname();
        this.email = user.getEmail();
        this.rating = user.getUserRating();
        this.lastLogin = user.getLastLogin();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Map<ReportReason, Integer> getReportList() {
        return reportList;
    }

    public void setReportList(Map<ReportReason, Integer> reportList) {
        this.reportList = reportList;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }
}
