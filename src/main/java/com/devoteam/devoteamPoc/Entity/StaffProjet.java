package com.devoteam.devoteamPoc.Entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "staff_Projet")
public class StaffProjet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id", nullable = false)
    private String userId;
    @Column(name = "project_title")
    private String projectTitle;

    @Temporal(TemporalType.DATE)
    @Column(name = "project_start_date")
    private Date projectStartDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "project_end_date")
    private Date projectEndDate;

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

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    public Date getProjectStartDate() {
        return projectStartDate;
    }

    public void setProjectStartDate(Date projectStartDate) {
        this.projectStartDate = projectStartDate;
    }

    public Date getProjectEndDate() {
        return projectEndDate;
    }

    public void setProjectEndDate(Date projectEndDate) {
        this.projectEndDate = projectEndDate;
    }
}
