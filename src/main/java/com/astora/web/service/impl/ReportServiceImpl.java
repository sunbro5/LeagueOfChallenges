package com.astora.web.service.impl;

import com.astora.web.dao.ReportDao;
import com.astora.web.dao.UserDao;
import com.astora.web.dao.model.Report;
import com.astora.web.dao.model.User;
import com.astora.web.enums.ReportReason;
import com.astora.web.exception.ServiceException;
import com.astora.web.exception.UserDoesntExists;
import com.astora.web.model.UserReportModel;
import com.astora.web.service.ReportService;
import com.astora.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author <a href="mailto:maresjan694@gmail.com">Jan Mares</a>, 16.11.2017
 */
@Service("reportService")
@Transactional
public class ReportServiceImpl implements ReportService {

    @Autowired
    private UserService userService;

    @Autowired
    private ReportDao reportDao;

    public void createReport(int userId, UserReportModel reportModel) throws ServiceException {
        User user = userService.getUserById(userId);
        User reportedUser = userService.getUserByNickname(reportModel.getNickname());
        try {
            ReportReason.valueOf(reportModel.getReason());
        } catch (IllegalArgumentException e) {
            throw new ServiceException("Cannot convert reason to enum: " + reportModel.getReason());
        }
        Report report = new Report();
        report.setReason(reportModel.getReason());
        report.setReasonText(reportModel.getReasonText());
        report.setUserByReportedUserId(reportedUser);
        report.setUserByReportingUserId(user);
        reportDao.create(report);
    }

}
