package com.example.fintechr.repository;

import com.example.fintechr.model.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
@RequiredArgsConstructor
public class EmployeeRepository {
    private final List<Employee> employees = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(0);


    public List<Employee> findAll() {
        return employees;
    }

    public Optional<Employee> findById(Long id) {
        return employees.stream()
                .filter(employee -> employee.getId().equals(id))
                .findFirst();
    }

    public Employee save(Employee employee) {
        employee.setId(idCounter.incrementAndGet());
        employees.add(employee);

        return employees.getLast();
    }

    public void deleteById(Long id) {
        employees.stream()
                .filter(employee -> employee.getId().equals(id))
                .findFirst()
                .ifPresent(employees::remove);
      
    public Optional<Employee> replace(Employee employee) {
        Optional<Employee> existingEmployee = findById(employee.getId());

        if (existingEmployee.isEmpty()) {
            return Optional.empty();
        }

        employees.remove(existingEmployee.get());
        employees.add(employee);
        return Optional.of(employee);
    }
}
