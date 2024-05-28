package com.devoteam.devoteamPoc.Dto;
import java.util.Date;
import java.util.List;

public class StaffDetailsDTO {
    private Long id;

    private List<String> certifications;


    private Date visa;
    private String projectTitle;
    private Date projectStartDate;
    private Date projectEndDate;

    private String disponibilte ;

    public String getDisponibilte() {
        return disponibilte;
    }

    public void setDisponibilte(String disponibilte) {
        this.disponibilte = disponibilte;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public List<String> getCertifications() {
        return certifications;
    }

    public void setCertifications(List<String> certifications) {
        this.certifications = certifications;
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
