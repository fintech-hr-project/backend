package com.example.fintechr.repository;

import com.example.fintechr.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepository {
    public List<Employee> findAll() {
        return List.of();
    }
}
