package com.astora.web.dao.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Created by to068466 on 29.10.2017.
 */
@Entity
public class Report {
    private int reportId;
    private Timestamp created;
    private String reason;
    private String reasonText;
    private int reportingUserId;
    private int reportedUserId;

    @Id
    @Column(name = "report_id")
    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    @Basic
    @Column(name = "created")
    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    @Basic
    @Column(name = "reason")
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Basic
    @Column(name = "reason_text")
    public String getReasonText() {
        return reasonText;
    }

    public void setReasonText(String reasonText) {
        this.reasonText = reasonText;
    }

    @Basic
    @Column(name = "reporting_user_id")
    public int getReportingUserId() {
        return reportingUserId;
    }

    public void setReportingUserId(int reportingUserId) {
        this.reportingUserId = reportingUserId;
    }

    @Basic
    @Column(name = "reported_user_id")
    public int getReportedUserId() {
        return reportedUserId;
    }

    public void setReportedUserId(int reportedUserId) {
        this.reportedUserId = reportedUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Report report = (Report) o;

        if (reportId != report.reportId) return false;
        if (reportingUserId != report.reportingUserId) return false;
        if (reportedUserId != report.reportedUserId) return false;
        if (created != null ? !created.equals(report.created) : report.created != null) return false;
        if (reason != null ? !reason.equals(report.reason) : report.reason != null) return false;
        if (reasonText != null ? !reasonText.equals(report.reasonText) : report.reasonText != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = reportId;
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + (reason != null ? reason.hashCode() : 0);
        result = 31 * result + (reasonText != null ? reasonText.hashCode() : 0);
        result = 31 * result + reportingUserId;
        result = 31 * result + reportedUserId;
        return result;
    }
}
