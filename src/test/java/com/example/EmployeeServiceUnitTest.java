package com.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeServiceUnitTest {

    @Test
    void testEmployeeName() {

        Employee employee = new Employee(1, "John");

        EmployeeService service = new EmployeeService();

        assertEquals("John", service.getEmployeeName(employee));
    }

    @Test
    void testBonusCalculation() {

        EmployeeService service = new EmployeeService();

        assertEquals(1000, service.calculateBonus(10000));
    }
}
