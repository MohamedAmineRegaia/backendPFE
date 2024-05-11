package com.devoteam.devoteamPoc.response;

public class LoginResponse {


   public String message;
   public Boolean status;
    private String employeename;
    private String password;
    private String email;
    private String Role;


    public LoginResponse(String message, Boolean status, String employeename, String password, String email, String role) {
        this.message = message;
        this.status = status;
        this.employeename = employeename;
        this.password = password;
        this.email = email;
        Role = role;
    }

    public LoginResponse(String message, Boolean status) {
        this.message = message;
        this.status = status;
    }

    public LoginResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

        public void setStatus(Boolean status) {
        this.status = status;
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
        return "LoginResponse{" +
                "message='" + message + '\'' +
                ", status=" + status +
                ", employeename='" + employeename + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", Role='" + Role + '\'' +
                '}';
    }
}
