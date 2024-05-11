package com.devoteam.devoteamPoc.EmployeeController;


import com.devoteam.devoteamPoc.Dto.EmployeeDTO;
import com.devoteam.devoteamPoc.Dto.LoginDTO;
import com.devoteam.devoteamPoc.Service.EmployeeService;
import com.devoteam.devoteamPoc.response.LoginResponse;
import jakarta.servlet.http.PushBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/v1/employee")

public class EmployeeController  {

    @Autowired
    private EmployeeService employeeService;




    @PostMapping(path = "/save")
    public String saveEmployee (@RequestBody EmployeeDTO employeeDTO) {

         String employee ;
         employee = employeeService.addEmployee(employeeDTO);


        return employee ;
    }


    @PostMapping(path = "/login")
    public ResponseEntity<?> loginEmployee(@RequestBody LoginDTO loginDTO){

        LoginResponse loginResponse = employeeService.loginEmployee(loginDTO);


        return ResponseEntity.ok(loginResponse);
    }


}
