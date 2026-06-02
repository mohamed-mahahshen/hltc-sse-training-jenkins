package com.example;

public class EmployeeService {

    public String getEmployeeName(Employee employee) {
        return employee.getName();
    }

    public int calculateBonus(int salary) {
        return salary * 10 / 100;
    }
}
