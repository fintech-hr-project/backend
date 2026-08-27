package com.example.fintechr.service;

import com.example.fintechr.exception.custom.EmployeeNotFoundException;
import com.example.fintechr.model.Employee;
import com.example.fintechr.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    public Employee findEmployeeById(Long id) {
        var employee = employeeRepository.findById(id);
        return employee.orElseThrow(EmployeeNotFoundException::new);
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void deleteEmployeeById(Long id) {
        findEmployeeById(id);
        employeeRepository.deleteById(id);
    }
        
    public Employee replaceEmployee(Employee employee) {
        return employeeRepository.replace(employee)
                .orElseThrow(() -> new EmployeeNotFoundException(employee.getId()));
    }
}
