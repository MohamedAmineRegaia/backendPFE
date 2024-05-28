package com.devoteam.devoteamPoc.Entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Visa")
public class Visa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id", nullable = false)
    private String userId;
    @Temporal(TemporalType.DATE)
    @Column(name = "visa")
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
