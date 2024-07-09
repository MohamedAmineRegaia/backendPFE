package com.devoteam.devoteamPoc.Dto;

import com.devoteam.devoteamPoc.Entity.Propale;
import jakarta.persistence.*;

import java.util.Date;

public class ProjectAssignmentDTO {

    private Long id;


    private Propale propale;

    private String userId;

    private String username;


    private Date assignmentDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Propale getPropale() {
        return propale;
    }

    public void setPropale(Propale propale) {
        this.propale = propale;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getAssignmentDate() {
        return assignmentDate;
    }

    public void setAssignmentDate(Date assignmentDate) {
        this.assignmentDate = assignmentDate;
    }
}
