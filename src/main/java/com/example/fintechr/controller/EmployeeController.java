package com.example.fintechr.controller;

import com.example.fintechr.model.Employee;
import com.example.fintechr.service.EmployeeService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.findEmployeeById(id));
    }

    @PostMapping
    public ResponseEntity<Employee> registerEmployee(@RequestBody Employee employee) {
        Employee createdEmployee = employeeService.createEmployee(employee);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdEmployee);
    }

}
