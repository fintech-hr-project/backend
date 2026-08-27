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
    void givenEmployeesExistWhenGetAllThenReturnAllEmployees() {
        var employees = List.of(
                createEmployee("John Doe", "john.email@email.com"),
                createEmployee("Bob", "bob.ishere@email.com"),
                createEmployee("Alice", "alice.inwonder@email.com")
        );

        when(repository.findAll()).thenReturn(employees);

        var result = service.getAll();

        assertEquals(employees, result);
        verify(repository).findAll();
    }

    @Test
    void givenValidEmployeeWhenCreateEmployeeThenReturnSavedEmployee() {
        var employee1 = createEmployee("John Doe", "john.email@email.com");

        when(repository.save(employee1)).thenReturn(employee1);
        
        var result = service.createEmployee(employee1);

        assertEquals(employee1, result);
        verify(repository).save(employee1);
    }

    @Test
    void givenEmployeeIdWhenFindEmployeeByIdThenReturnEmployee() {
        var employee = createEmployee("John Doe", "john.email@email.com");

        when(repository.findById(1)).thenReturn(employee);

        var result = service.findEmployeeById(1);

        assertEquals(employee, result);
        verify(repository).findAll();
    }

    private Employee createEmployee(String name, String email) {
        return Employee.builder()
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
