package com.devoteam.devoteamPoc.Entity;

import jakarta.persistence.Embeddable;

import java.util.Date;

@Embeddable
public class HistoryEntry {
    private String action;
    private String user;
    private Date timestamp;

    private String PropaleName;
    private String oldValue;
    private String newValue;

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    public String getPropaleName() {
        return PropaleName;
    }

    public void setPropaleName(String propaleName) {
        PropaleName = propaleName;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}