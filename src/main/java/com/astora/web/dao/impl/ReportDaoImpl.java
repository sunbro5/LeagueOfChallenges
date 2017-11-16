package com.astora.web.dao.impl;


import com.astora.web.dao.ReportDao;
import com.astora.web.dao.model.Report;
import org.springframework.stereotype.Repository;

/**
 * @author <a href="mailto:maresjan694@gmail.cz">Jan Mares</a>, 16.11.2017
 */
@Repository("reportDao")
public class ReportDaoImpl extends EntityDaoImpl<Report> implements ReportDao {

    protected ReportDaoImpl() {
        super(Report.class);
    }
}
