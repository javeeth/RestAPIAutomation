package com.atlassian.api.builder;

import com.atlassian.api.entities.Employee;

public class EmployeeBuilder {

    private Employee employee;

    public EmployeeBuilder() {
        this.employee = new Employee();
    }

    public EmployeeBuilder withName(String name) {
        employee.setName(name);
        return this;
    }

    public Employee build() {
        return employee;
    }
}