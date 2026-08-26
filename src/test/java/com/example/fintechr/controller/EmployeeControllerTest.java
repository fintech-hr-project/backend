package com.example.fintechr.controller;

import com.example.fintechr.model.Employee;
import com.example.fintechr.model.enums.Status;
import com.example.fintechr.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private EmployeeService service;

    @Test
    void shouldReturnAllEmployees() throws Exception {
        var employees = List.of(
                createEmployee("John Doe", "john@email.com"),
                createEmployee("Bob", "bob@email.com")
        );

        when(service.getAll()).thenReturn(employees);

        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void shouldReturnSavedEmployee() throws Exception {
        var employee = createEmployee("John Doe", "john@email.com");

        when(service.createEmployee(employee)).thenReturn(employee);

        ObjectMapper objectMapper = new ObjectMapper();

        var employeeJson = objectMapper.writeValueAsString(employee)

        mockMvc.perform(post("/employees")
                .contentType("application/json")
                .content(employeeJson))
                .andExpect(status().isCreated())
                .andExpect(content().json(employeeJson));
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
