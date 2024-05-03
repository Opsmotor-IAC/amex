package com.amex.demo.controllers;

import com.amex.demo.constants.CommonConstant;
import com.amex.demo.dto.responses.CommonResponse;
import com.amex.demo.dto.responses.Employee;
import com.amex.demo.entities.EmployeeEntity;
import com.amex.demo.services.EmployeeService;
import com.amex.demo.constants.CommonConstant.*;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/amex/employee")
@Slf4j
public class EmployeeController {

    @Autowired
    EmployeeService service;


    @GetMapping(value = "/{employeeId}/details", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> getEmployeeDetails(@PathVariable(value = "employeeId") String empId) {
        try {
            //this isnt the ideal way to send response like this, with non-null param manipulation
            //for ease , I have used the Json-Ignore annotation and used a generic response, ideally this should be handled by ControllerAdvice with custom exception
            return new ResponseEntity<>(service.getEmployeeDetails(empId), HttpStatus.OK);
        }catch(RuntimeException e){
            Employee response = Employee.builder()
                    .message(e.getMessage())
                    .status(CommonConstant.FAILED)
                    .build();
           return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResponse> createEmployee(@Valid @RequestBody EmployeeEntity entity) {
        CommonResponse response =  service.createNewEmployee(entity);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{employeeId}/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResponse> deleteEmployee(
            @PathVariable(value = "employeeId", required = true) String employeeId) {
        log.info("deleting employee: {}", employeeId);
        CommonResponse response =  service.deleteEmployeeFromSystem(employeeId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResponse> updateEmployee(@RequestBody EmployeeEntity entity) {
        //this is in an assumption that UI sends entire entity data, we can also do it by adding request param for employee ID and the changed data as well
        CommonResponse response =  service.updateEmployeeDetails(entity);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
