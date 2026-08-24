package com.example.fintechr.repository;

import com.example.fintechr.model.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class EmployeeRepository {
    private final List<Employee> employees;

    public List<Employee> findAll() {
        return List.of();
    }
}
