package com.atlassian.apitesting.dataProviders;

import com.atlassian.api.entities.Employee;
import org.testng.annotations.DataProvider;

import java.util.Random;

public class EmployeeProvider {

    @DataProvider(name = "employee")
    public Object[][] getEmployee() {
        return new Object[][] {{generateEmployee()}};
    }

    private Employee generateEmployee() {
        Employee employee = new Employee();
        employee.setName("Tiger Nixon");
        employee.setSalary("320800");
        employee.setAge("61");
        return employee;
    }
}
