package com.example.fintechr.service;

import com.example.fintechr.model.Employee;
import com.example.fintechr.model.enums.Status;
import com.example.fintechr.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
    @Mock
    private EmployeeRepository repository;
    @InjectMocks
    private EmployeeService service;

    @Test
    void shouldReturnAllEmployees() {
        var employees = List.of(
                createEmployee(1L, "John Doe", "john.email@email.com"),
                createEmployee(2L, "Bob", "bob.ishere@email.com"),
                createEmployee(3L, "Alice", "alice.inwonder@email.com")
        );

        when(repository.findAll()).thenReturn(employees);

        var result = service.getAll();

        assertEquals(employees, result);
        verify(repository).findAll();
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
