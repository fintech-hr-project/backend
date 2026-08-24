package com.example.fintechr.service;

import com.example.fintechr.model.Employee;
import com.example.fintechr.model.enums.Status;
import com.example.fintechr.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmployeeRepositoryTest {

    private final EmployeeRepository repository = new EmployeeRepository();

    @Test
    void shouldReturnAllEmployees() {
        var employee1 = createEmployee(1L, "John Doe", "john.email@email.com");
        var employee2 = createEmployee(2L, "Bob", "bob.ishere@email.com");
        var employee3 = createEmployee(3L, "Alice", "alice.inwonder@email.com");

        repository.save(employee1);
        repository.save(employee2);
        repository.save(employee3);

        var result = repository.findAll();

        assertEquals(List.of(employee1, employee2, employee3), result);
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