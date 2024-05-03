package com.amex.demo.services.serviceImpl;

import com.amex.demo.constants.CommonConstant;
import com.amex.demo.dto.responses.CommonResponse;
import com.amex.demo.dto.responses.Employee;
import com.amex.demo.entities.EmployeeEntity;
import com.amex.demo.repositories.EmployeeRepository;
import com.amex.demo.services.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository repository;

    @Override
    public CommonResponse createNewEmployee(EmployeeEntity request) {
        repository.save(request);
        log.info("Successfully created");
        return CommonResponse.builder()
                .message("Employee successfully created")
                .status(CommonConstant.SUCCESS)
                .build();
    }

    @Override
    public Employee getEmployeeDetails(String employeeId) {
        Optional<EmployeeEntity> entities = repository.findById(employeeId);
        EmployeeEntity entity = null;
        if(entities.isPresent()) {
            entity = entities.get();
            return Employee.builder()
                    .id(entity.getId())
                    .name(entity.getName())
                    .mailId(entity.getEmail())
                    .build();
        }
        //if the employeee value isnt returned, this will throw an error, this can be handled in many ways with custom exceptions
        throw new RuntimeException("Employee is not present in the system");
    }

    @Override
    public CommonResponse deleteEmployeeFromSystem(String employeeId) {
        //assuming employee is present in DB and validated from UI, else, a get call validation needs to be added
        repository.deleteById(employeeId);
        return  CommonResponse.builder()
                .message("Employee successfully deleted")
                .status(CommonConstant.SUCCESS)
                .build();
    }

    @Override
    public CommonResponse updateEmployeeDetails(EmployeeEntity request) {
        if(request.getId()==null){
            throw new RuntimeException("Please confirm the Employye ID");
        }
        repository.save(request);
        //in this method, if the employee with ID is present it will update that particular entry, else create a new entry
        //So if we need to check if employee is present, a get call validation needs to be added ,(not mentioned in problem statement)
        return CommonResponse.builder()
                .message("Employee successfully updated")
                .status(CommonConstant.SUCCESS)
                .build();
    }
}
