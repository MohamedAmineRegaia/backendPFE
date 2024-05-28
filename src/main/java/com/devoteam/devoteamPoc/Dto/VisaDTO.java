package com.devoteam.devoteamPoc.Dto;

import java.util.Date;

public class VisaDTO {

    private Long id;
    private String userId ;

    private Date visa;

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

    public Date getVisa() {
        return visa;
    }

    public void setVisa(Date visa) {
        this.visa = visa;
    }
}
