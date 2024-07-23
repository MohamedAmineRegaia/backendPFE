package com.devoteam.devoteamPoc.Entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="propale")
public class Propale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "company_org")
    private String company_org ;
    @Column(name = "propaleName")
    private String propaleName ;
    @Column(name = "account_Type")
    private String account_Type ;
    @Column(name = "status")
    private String status ;

    @Temporal(TemporalType.DATE)
    @Column(name = "SubmissionDate")
    private Date SubmissionDate ;
    @Column(name = "bid_manager")
    private String bid_manager ;
    @Column(name = "CRP_CRD")
    private String CRP_CRD ;
    @Column(name = "typeOfRequest")
    private String typeOfRequest ;
    @Column(name = "practice")
    private String practice ;
    @Column(name = "primaryVp")
    private String primaryVp ;
    @Column(name = "principalVendor")
    private String principalVendor ;
    @Column(name = "secondaryVendor")
    private String secondaryVendor ;
    @Column(name = "budget_no_vat")
    private String budget_no_vat;
    @Column(name = "estimated_value")
    private String estimated_value;
    @Column(name = "lgAmount")
    private String lgAmount;
    @Temporal(TemporalType.DATE)
    @Column(name = "QA_deadline",nullable = true)
    private Date QA_deadline;
    @Temporal(TemporalType.DATE)
    @Column(name = "ReviewDate",nullable = true)
    private Date ReviewDate;
    @Column(name = "noGoReason")
    private String noGoReason;

    @Column(name = "noGoDescription",nullable = true)
    private String noGoDescription;
    @ElementCollection
    private List<HistoryEntry> historyEntries;

    @OneToMany(mappedBy = "propale", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ProjectAssignment> projectAssignments;





    public String getNoGoDescription() {
        return noGoDescription;
    }

    public Date getReviewDate() {
        return ReviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        ReviewDate = reviewDate;
    }

    public void setNoGoDescription(String noGoDescription) {
        this.noGoDescription = noGoDescription;
    }



    public Long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<ProjectAssignment> getProjectAssignments() {
        return projectAssignments;
    }

    public void setProjectAssignments(List<ProjectAssignment> projectAssignments) {
        this.projectAssignments = projectAssignments;
    }
}
