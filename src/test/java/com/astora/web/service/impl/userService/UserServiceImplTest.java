package com.astora.web.service.impl.userService;

import com.astora.web.dao.model.Report;
import com.astora.web.dao.model.User;
import com.astora.web.dto.UserInfoDto;
import com.astora.web.enums.ReportReason;
import com.astora.web.service.impl.UserServiceImpl;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author <a href="mailto:maresjan694@gmail.com">Jan Mares</a>, 7.12.2017
 */

public class UserServiceImplTest {

    @Test
    public void testGetUserInfoByNickname() throws Exception {
        String nickname = "nickname";
        String firstName = "firstName";
        String email = "email@o.c";
        int rating  = 20;
        Class userService = UserServiceImpl.class;
        Method method = userService.getDeclaredMethod("mapUserInfo", User.class);
        method.setAccessible(true);
        Object i = userService.newInstance();
        User user = new User();
        user.setNickname(nickname);
        user.setFirstName(firstName);
        user.setUserRating(rating);
        user.setEmail(email);
        List<Report> reportList = new ArrayList<Report>();
        Report report = new Report();
        report.setReason(ReportReason.USER_CHEATER.name());
        reportList.add(report);
        report = new Report();
        report.setReason(ReportReason.USER_FAIR_PLAY.name());
        reportList.add(report);
        report = new Report();
        report.setReason(ReportReason.USER_CHEATER.name());
        reportList.add(report);
        report = new Report();
        report.setReason(ReportReason.USER_GOOD_TO_PLAY_WITH.name());
        reportList.add(report);
        report = new Report();
        report.setReason(ReportReason.USER_CHEATER.name());
        reportList.add(report);
        user.setReportsByUserId(reportList);

        UserInfoDto userInfoDto = (UserInfoDto) method.invoke(i,user);

        assertEquals(firstName, userInfoDto.getFirstName());
        assertEquals(nickname, userInfoDto.getNickname());
        assertEquals(email, userInfoDto.getEmail());
        assertEquals(rating, userInfoDto.getRating());

        Map<ReportReason, Integer> reports = userInfoDto.getReportList();
        int cheaterCount = reports.get(ReportReason.USER_CHEATER);
        int fairPlayCount = reports.get(ReportReason.USER_FAIR_PLAY);
        assertEquals(3,cheaterCount);
        assertEquals(1,fairPlayCount);
    }
}