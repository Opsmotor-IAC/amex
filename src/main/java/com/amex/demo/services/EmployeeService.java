package com.amex.demo.services;

import com.amex.demo.dto.responses.CommonResponse;
import com.amex.demo.dto.responses.Employee;
import com.amex.demo.entities.EmployeeEntity;
import org.springframework.stereotype.Service;

@Service
public interface EmployeeService {

    public CommonResponse createNewEmployee(EmployeeEntity request);

    public Employee getEmployeeDetails(String employeeId);

    public CommonResponse deleteEmployeeFromSystem(String employeeId);

    public CommonResponse updateEmployeeDetails(EmployeeEntity request);
}
