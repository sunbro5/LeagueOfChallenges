package com.astora.web.service;

import com.astora.web.exception.ServiceException;
import com.astora.web.model.UserReportModel;

/**
 * @author <a href="mailto:maresjan694@gmail.com">Jan Mares</a>, 16.11.2017
 */
public interface ReportService {

    void createReport(int userId, UserReportModel reportModel) throws ServiceException;
}
