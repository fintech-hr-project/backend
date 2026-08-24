package com.example.fintechr.repository;

import com.example.fintechr.model.Employee;
import com.example.fintechr.model.enums.Status;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmployeeRepositoryTest {

    private EmployeeRepository repository;

    @Test
    void shouldReturnAllEmployees() {
        var employees = List.of(
                createEmployee(1L, "John Doe", "john.email@email.com"),
                createEmployee(2L, "Bob", "bob.ishere@email.com"),
                createEmployee(3L, "Alice", "alice.inwonder@email.com")
        );

        repository = new EmployeeRepository(employees);

        var result = repository.findAll();

        assertEquals(employees, result);
    }

    private Employee createEmployee(Long id, String name, String email) {
        return Employee.builder()
                .id(id)
                .name(name)
                .email(email)
                .phone("11999999999")
                .role("Software Engineer")
                .department("Technology")
                .salary(new BigDecimal("7500.00"))
                .city("São Paulo")
                .status(Status.HIRED)
                .build();
    }
}