package com.devoteam.devoteamPoc.Entity;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "staff_details")
public class StaffDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @ElementCollection
    @CollectionTable(name = "staff_certifications", joinColumns = @JoinColumn(name = "staff_id"))
    @Column(name = "certification")
    private List<String> certif;

    @Temporal(TemporalType.DATE)
    @Column(name = "visa")
    private Date visa;

    @Column(name = "project_title")
    private String projectTitle;

    @Temporal(TemporalType.DATE)
    @Column(name = "project_start_date")
    private Date projectStartDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "project_end_date")
    private Date projectEndDate;
    @Column(name = "disponibilte")
    private String disponibilte ;

    public String getDisponibilte() {
        return disponibilte;
    }

    public void setDisponibilte(String disponibilte) {
        this.disponibilte = disponibilte;
    }

// Getters and setters

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

    public List<String> getCertif() {
        return certif;
    }

    public void setCertif(List<String> certif) {
        this.certif = certif;
    }

    public Date getVisa() {
        return visa;
    }

    public void setVisa(Date visa) {
        this.visa = visa;
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
