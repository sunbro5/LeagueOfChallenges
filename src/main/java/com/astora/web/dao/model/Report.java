package com.astora.web.dao.model;

import com.astora.web.enums.ReportReason;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by to068466 on 29.10.2017.
 */
@Entity
public class Report {
    private int reportId;
    private Timestamp created;
    private ReportReason reason;
    private String reasonText;
    private User userByReportingUserId;
    private User userByReportedUserId;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "report_id")
    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    @Basic
    @Column(name = "created", insertable = false)
    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    @Basic
    @Column(name = "reason")
    @Enumerated(EnumType.STRING)
    public ReportReason getReason() {
        return reason;
    }

    public void setReason(ReportReason reason) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Report report = (Report) o;

        if (reportId != report.reportId) return false;
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
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "reporting_user_id", referencedColumnName = "user_id", nullable = false)
    public User getUserByReportingUserId() {
        return userByReportingUserId;
    }

    public void setUserByReportingUserId(User userByReportingUserId) {
        this.userByReportingUserId = userByReportingUserId;
    }

    @ManyToOne
    @JoinColumn(name = "reported_user_id", referencedColumnName = "user_id", nullable = false)
    public User getUserByReportedUserId() {
        return userByReportedUserId;
    }

    public void setUserByReportedUserId(User userByReportedUserId) {
        this.userByReportedUserId = userByReportedUserId;
    }
}
