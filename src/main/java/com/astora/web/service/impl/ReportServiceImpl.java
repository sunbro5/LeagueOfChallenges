package com.astora.web.service.impl;

import com.astora.web.dao.ReportDao;
import com.astora.web.dao.UserDao;
import com.astora.web.dao.model.Report;
import com.astora.web.dao.model.User;
import com.astora.web.dto.UserReportInfo;
import com.astora.web.enums.ReportReason;
import com.astora.web.exception.ServiceException;
import com.astora.web.exception.UserDoesntExists;
import com.astora.web.model.UserReportModel;
import com.astora.web.service.ReportService;
import com.astora.web.service.UserService;
import com.astora.web.utils.CustomValidationUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:maresjan694@gmail.com">Jan Mares</a>, 16.11.2017
 */
@Service("reportService")
@Transactional
public class ReportServiceImpl implements ReportService {

    private static final Logger logger = Logger.getLogger(ReportServiceImpl.class);

    @Autowired
    private UserService userService;

    @Autowired
    private ReportDao reportDao;

    public void createUpdateReport(int userId, UserReportModel reportModel) throws ServiceException {
        User user = userService.getUserById(userId);
        User reportedUser = userService.getUserByNickname(reportModel.getNickname());
        if(user.equals(reportedUser)){
            return;
        }
        ReportReason reason;
        try {
            reason = ReportReason.valueOf(reportModel.getReason());
        } catch (IllegalArgumentException e) {
            throw new ServiceException("Cannot convert reason to enum: " + reportModel.getReason());
        }
        List<Report> reports = reportDao.getReportWithUsers(user, reportedUser);
        if (!CustomValidationUtils.isEmpty(reports)) {
            if (reports.size() > 1) {
                logger.info("There should be just one report, userId: " + userId);
            }
            Report report = reports.get(0);
            report.setReason(reportModel.getReason());
            report.setReasonText(reportModel.getReasonText());
            report.setUserByReportedUserId(reportedUser);
            report.setUserByReportingUserId(user);
            reportDao.update(report);
            calculateUserRating(reportedUser,reason,report);

        } else {
            Report report = new Report();
            report.setReason(reportModel.getReason());
            report.setReasonText(reportModel.getReasonText());
            report.setUserByReportedUserId(reportedUser);
            report.setUserByReportingUserId(user);
            reportDao.create(report);
            calculateUserRating(reportedUser,reason,null);
        }
    }

    public List<UserReportInfo> getUserReports(int userId) throws ServiceException {
        User user = userService.getUserById(userId);
        List<UserReportInfo> reportsInfo = new ArrayList<UserReportInfo>();
        for (Report report : user.getReportsByUserId()) {
            UserReportInfo reportInfo = new UserReportInfo();
            reportInfo.setNickname(report.getUserByReportedUserId().getNickname());
            try {
                reportInfo.setReason(ReportReason.valueOf(report.getReason()));
            } catch (IllegalArgumentException e) {
                reportInfo.setReason(ReportReason.getDefault());
                logger.error(e);
            }
            reportsInfo.add(reportInfo);
        }
        return reportsInfo;
    }

    /**
     * Update user rating
     * @param user
     * @param reason
     * @param oldReport
     * @throws ServiceException
     */
    private void calculateUserRating(User user, ReportReason reason, Report oldReport) throws ServiceException {
        int rating = user.getUserRating();
        if(oldReport == null){
            user.setUserRating(rating + reason.getValue());
        } else {
            ReportReason oldReason;
            try{
                oldReason = ReportReason.valueOf(oldReport.getReason());
            } catch (IllegalArgumentException e){
                logger.error(e);
                throw new ServiceException("Cannot parse report reason, reportId: " + oldReport.getReportId());
            }
            rating = rating - oldReason.getValue();
            user.setUserRating(rating + reason.getValue());
        }
        userService.updateUser(user);
    }

}
