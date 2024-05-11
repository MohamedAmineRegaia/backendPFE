package com.devoteam.devoteamPoc.Service.impl;

import com.devoteam.devoteamPoc.Dto.EmployeeDTO;
import com.devoteam.devoteamPoc.Dto.LoginDTO;
import com.devoteam.devoteamPoc.Entity.Employee;
import com.devoteam.devoteamPoc.Repo.EmployeeRepo;
import com.devoteam.devoteamPoc.Service.EmployeeService;
import com.devoteam.devoteamPoc.response.LoginResponse;
import jdk.swing.interop.SwingInterOpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class EmployeeIMPL implements EmployeeService {


    @Autowired
    private EmployeeRepo  employeeRepo;



    @Override
    public String addEmployee(EmployeeDTO employeeDTO) {

        Employee existingEmployee = employeeRepo.findByEmail(employeeDTO.getEmail());

        if (existingEmployee != null) {

            return "Email already exists";
        }

        Employee employee = new Employee(
                employeeDTO.getEmployeeid(),
                employeeDTO.getEmployeename(),
                employeeDTO.getEmail(),
               employeeDTO.getPassword(),
                employeeDTO.getRole()
        );

        employeeRepo.save(employee);

        return "Employee added with success";
    }

    @Override
    public LoginResponse loginEmployee(LoginDTO loginDTO) {
        String msg = "";
        Employee employee1 = employeeRepo.findByEmail(loginDTO.getEmail());
        if (employee1 != null) {
            String password = loginDTO.getPassword();
            String encodedPassword = employee1.getPassword();
            Boolean isPwdRight = true;
            if (true) {
                Optional<Employee> employee = employeeRepo.findOneByEmailAndPassword(loginDTO.getEmail(), encodedPassword);

                if (employee.isPresent()) {
                    return new LoginResponse("Login Success",true, employee1.getEmployeename(), employee1.getPassword(),employee1.getEmail(),employee1.getRole());
                } else {
                    return new LoginResponse("Login Failed", false , employee1.getEmployeename(), employee1.getPassword(),employee1.getEmail(),employee1.getRole());
                }
            } else {
                return new LoginResponse("password Not Match", false , employee1.getEmployeename(), employee1.getPassword(),employee1.getEmail(),employee1.getRole());
            }
        }else {
            System.out.println("coucouc");
            return new LoginResponse("Email not exits", false,"test","test","test","test");
        }
    }






}
