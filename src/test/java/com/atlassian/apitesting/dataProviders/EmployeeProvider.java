package com.atlassian.apitesting.dataProviders;

import com.atlassian.api.entities.Employee;
import com.github.javafaker.Faker;
import org.testng.annotations.DataProvider;

import java.util.Locale;


public class EmployeeProvider {

    //Implementation for Faker will be provided later. The API will fail for random data.
    //Faker faker = new Faker(new Locale("en-GB"));

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
