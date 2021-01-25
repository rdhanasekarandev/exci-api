package com.exciteon.api;


import com.exciteon.modal.Employee;
import com.exciteon.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(value = "employee")
public class EmployeeSource {
    @Autowired
    private EmployeeService employeeService;



    //  add new employee
    @PostMapping("/save")
    public String saveEmployeeDetails(@RequestBody Employee employee){
        return employeeService.addEmployee(employee);
    }

    //  get all employee
    @CrossOrigin
    @GetMapping("/get")
    private List<Employee> getAllEmployee() throws ExecutionException, InterruptedException {
        return employeeService.getAllEmployee();
    }
}
