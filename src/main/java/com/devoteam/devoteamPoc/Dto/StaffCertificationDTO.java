package com.devoteam.devoteamPoc.Dto;

import java.util.List;

public class StaffCertificationDTO {
    private Long id;


    private String userId ;



    private String certification;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getCertification() {
        return certification;
    }

    public void setCertification(String certification) {
        this.certification = certification;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
