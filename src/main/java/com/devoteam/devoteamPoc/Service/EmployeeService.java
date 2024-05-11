package com.devoteam.devoteamPoc.Service;

import com.devoteam.devoteamPoc.Dto.EmployeeDTO;
import com.devoteam.devoteamPoc.Dto.LoginDTO;
import com.devoteam.devoteamPoc.response.LoginResponse;

public interface EmployeeService {



    String addEmployee(EmployeeDTO employeeDTO);

    LoginResponse loginEmployee(LoginDTO loginDTO);
}
