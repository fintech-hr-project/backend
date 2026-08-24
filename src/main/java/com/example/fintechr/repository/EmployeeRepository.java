package com.example.fintechr.repository;

import com.example.fintechr.model.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class EmployeeRepository {
    private final List<Employee> employees = new ArrayList<>();

    public List<Employee> findAll() {
        return employees;
    }

    public Employee save(Employee employee) {
        employees.add(employee);
        return employees.getLast();
    }
}
