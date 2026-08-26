package com.example.fintechr.repository;

import com.example.fintechr.model.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Repository
@RequiredArgsConstructor
public class EmployeeRepository {
    private final List<Employee> employees = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(0);


    public List<Employee> findAll() {
        return employees;
    }

    public Employee save(Employee employee) {
        employee.setId(idCounter.incrementAndGet());
        employees.add(employee);
        return employees.getLast();
    }
}
