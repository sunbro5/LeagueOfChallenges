package com.astora.web.service;

import com.astora.web.dto.UserReportInfo;
import com.astora.web.exception.ServiceException;
import com.astora.web.model.UserReportModel;

import java.util.List;

/**
 * @author <a href="mailto:maresjan694@gmail.com">Jan Mares</a>, 16.11.2017
 */
public interface ReportService {

    /**
     * create report
     * @param userId
     * @param reportModel
     * @throws ServiceException
     */
    void createUpdateReport(int userId, UserReportModel reportModel) throws ServiceException;

    /**
     * Return all userReportInfo for defined user
     * @param userId
     * @return
     * @throws ServiceException
     */
    List<UserReportInfo> getUserReports(int userId) throws ServiceException;
}
