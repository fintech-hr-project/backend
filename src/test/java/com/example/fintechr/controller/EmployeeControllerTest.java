package com.example.fintechr.controller;

import com.example.fintechr.model.Employee;
import com.example.fintechr.model.enums.Status;
import com.example.fintechr.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MockitoExtension.class)
@RequiredArgsConstructor
public class EmployeeControllerTest {
    private final MockMvc mockMvc;
    @MockitoBean
    private EmployeeService service;

    @Test
    void shouldReturnAllEmployees() throws Exception {
        var employees = List.of(
                createEmployee(1L, "John Doe", "john@email.com"),
                createEmployee(2L, "Bob", "bob@email.com")
        );

        when(service.getAll()).thenReturn(employees);

        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
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
