package com.devoteam.devoteamPoc.Dto;

import com.devoteam.devoteamPoc.Entity.HistoryEntry;
import com.devoteam.devoteamPoc.Entity.ProjectAssignment;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

public class PropaleDTO {

    private Long id;
    private String userId;
    private String company_org ;

    private String propaleName ;

    private String account_Type ;

    private String status ;

    private Date SubmissionDate ;

    private String bid_manager ;

    private String CRP_CRD ;

    private String typeOfRequest ;

    private String practice ;

    private String primaryVp ;
    private String principalVendor ;

    private String secondaryVendor ;

    private String budget_no_vat;

    private String estimated_value;
    private String lgAmount;

    private Date QA_deadline;

    private String noGoReason;
    private String noGoDescription;
    private Date ReviewDate;
    private List<ProjectAssignmentDTO> projectAssignments;

    private List<HistoryEntry> historyEntries;


    public Date getReviewDate() {
        return ReviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        ReviewDate = reviewDate;
    }

    public String getNoGoDescription() {
        return noGoDescription;
    }

    public void setNoGoDescription(String noGoDescription) {
        this.noGoDescription = noGoDescription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCompany_org() {
        return company_org;
    }

    public void setCompany_org(String company_org) {
        this.company_org = company_org;
    }

    public String getPropaleName() {
        return propaleName;
    }

    public void setPropaleName(String propaleName) {
        this.propaleName = propaleName;
    }

    public String getAccount_Type() {
        return account_Type;
    }

    public void setAccount_Type(String account_Type) {
        this.account_Type = account_Type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getSubmissionDate() {
        return SubmissionDate;
    }

    public void setSubmissionDate(Date submissionDate) {
        SubmissionDate = submissionDate;
    }

    public String getBid_manager() {
        return bid_manager;
    }

    public void setBid_manager(String bid_manager) {
        this.bid_manager = bid_manager;
    }

    public String getCRP_CRD() {
        return CRP_CRD;
    }

    public void setCRP_CRD(String CRP_CRD) {
        this.CRP_CRD = CRP_CRD;
    }

    public String getTypeOfRequest() {
        return typeOfRequest;
    }

    public void setTypeOfRequest(String typeOfRequest) {
        this.typeOfRequest = typeOfRequest;
    }

    public String getPractice() {
        return practice;
    }

    public void setPractice(String practice) {
        this.practice = practice;
    }

    public String getPrimaryVp() {
        return primaryVp;
    }

    public void setPrimaryVp(String primaryVp) {
        this.primaryVp = primaryVp;
    }

    public String getPrincipalVendor() {
        return principalVendor;
    }

    public void setPrincipalVendor(String principalVendor) {
        this.principalVendor = principalVendor;
    }

    public String getSecondaryVendor() {
        return secondaryVendor;
    }

    public void setSecondaryVendor(String secondaryVendor) {
        this.secondaryVendor = secondaryVendor;
    }

    public String getBudget_no_vat() {
        return budget_no_vat;
    }

    public void setBudget_no_vat(String budget_no_vat) {
        this.budget_no_vat = budget_no_vat;
    }

    public String getEstimated_value() {
        return estimated_value;
    }

    public void setEstimated_value(String estimated_value) {
        this.estimated_value = estimated_value;
    }

    public String getLgAmount() {
        return lgAmount;
    }

    public void setLgAmount(String lgAmount) {
        this.lgAmount = lgAmount;
    }

    public Date getQA_deadline() {
        return QA_deadline;
    }

    public void setQA_deadline(Date QA_deadline) {
        this.QA_deadline = QA_deadline;
    }

    public String getNoGoReason() {
        return noGoReason;
    }

    public void setNoGoReason(String noGoReason) {
        this.noGoReason = noGoReason;
    }

    public List<HistoryEntry> getHistoryEntries() {
        return historyEntries;
    }

    public void setHistoryEntries(List<HistoryEntry> historyEntries) {
        this.historyEntries = historyEntries;
    }

    public List<ProjectAssignmentDTO> getProjectAssignments() {
        return projectAssignments;
    }

    public void setProjectAssignments(List<ProjectAssignmentDTO> projectAssignments) {
        this.projectAssignments = projectAssignments;
    }
}
