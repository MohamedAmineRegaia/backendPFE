package com.devoteam.devoteamPoc.Dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class EmployeeDTO   {
    private int employeeid ;
    private String employeename;
    private String password;
    private String email;
    private String Role;

    public EmployeeDTO(int employeeid, String employeename, String password, String email, String role) {
        this.employeeid = employeeid;
        this.employeename = employeename;
        this.password = password;
        this.email = email;
        Role = role;
    }

    public EmployeeDTO() {

    }

    public int getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(int employeeid) {
        this.employeeid = employeeid;
    }

    public String getEmployeename() {
        return employeename;
    }

    public void setEmployeename(String employeename) {
        this.employeename = employeename;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "employeeid=" + employeeid +
                ", employeename='" + employeename + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", Role='" + Role + '\'' +
                '}';
    }
}
