package com.astora.web.dao;

import com.astora.web.dao.model.Report;
import com.astora.web.dao.model.User;

import java.util.List;

/**
 * @author <a href="mailto:maresjan694@gmail.com">Jan Mares</a>, 16.11.2017
 */
public interface ReportDao extends EntityDao<Report> {

    List<Report> getReportWithUsers(User user1Id, User user2Id);
}
