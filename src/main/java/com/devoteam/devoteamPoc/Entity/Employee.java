package com.devoteam.devoteamPoc.Entity;


import jakarta.persistence.*;

@Entity
@Table(name="employee")
public class Employee  {

    @Id
    @Column(name="employee_id", length = 25)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int employeeid ;

    @Column(name="employee_name", length = 255)
    private String employeename;

    @Column(name="email", length = 255)
    private String email;
    @Column(name="password", length = 255)
    private String password;
    @Column(name="Role", length = 255)
    private String role;


    public Employee(int employeeid, String employeename, String email, String password, String role) {
        this.employeeid = employeeid;
        this.employeename = employeename;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Employee(){

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeid=" + employeeid +
                ", employeename='" + employeename + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
