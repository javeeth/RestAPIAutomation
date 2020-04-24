package com.atlassian.apitesting.tests;

import com.atlassian.api.entities.*;
import com.atlassian.api.property.Environment;
import com.atlassian.api.property.SystemProperties;
import com.atlassian.apitesting.Group;
import com.atlassian.apitesting.apiHelper.EmployeeTestHelper;
import com.atlassian.apitesting.dataProviders.EmployeeProvider;
import com.google.gson.Gson;
import org.aeonbits.owner.ConfigFactory;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Optional;
import java.util.logging.Logger;

public class EmployeeTests {

    Environment testEnvironment;
    EmployeeTestHelper employeeTestHelper;
    Logger logger = Logger.getLogger(EmployeeTests.class.getName());
    Gson gson = new Gson();

    @BeforeMethod
    void initializeApiContext(){
        ConfigFactory.setProperty("env", SystemProperties.ENV);
        testEnvironment = ConfigFactory.create(Environment.class);
        logger.info("Test Environment is : " + SystemProperties.ENV);
        employeeTestHelper = new EmployeeTestHelper(testEnvironment);
    }

    @Test(groups = {Group.REGRESSION, Group.SMOKE})
    public void getEmployeeDetails(){
        RestResponse<GetEmployeesRsp> getEmployeesRsp = employeeTestHelper.getEmployees();
        logger.info("GetEmployee Response  : \n" + gson.toJson(getEmployeesRsp.getApiResponse()));
        Assert.assertEquals(HttpStatus.SC_OK, getEmployeesRsp.getStatusCode());
    }

    @Test(groups = {Group.REGRESSION, Group.SMOKE}, dataProvider = "employee", dataProviderClass = EmployeeProvider.class)
    public void postEmployeeDetails(Employee employee){

        RestResponse<PostEmployeeRsp> postEmployeeRsp = employeeTestHelper.postEmployee(employee);
        logger.info("GetEmployee Response  : \n" + gson.toJson(postEmployeeRsp.getApiResponse()));

        Assert.assertEquals(postEmployeeRsp.getApiResponse().getData().getName(), employee.getName());
        Assert.assertEquals(postEmployeeRsp.getApiResponse().getData().getAge(), employee.getAge());
        Assert.assertEquals(postEmployeeRsp.getApiResponse().getData().getSalary(), employee.getSalary());

        RestResponse<GetEmployeesRsp> getEmployeesRsp = employeeTestHelper.getEmployees();
        logger.info("GetEmployee Response  : \n" + gson.toJson(getEmployeesRsp.getApiResponse()));

        Optional<EmployeeData> employeeData =  getEmployeesRsp.getApiResponse().getData().stream().filter(i -> i.getId().equals("1")).findAny();

        Assert.assertEquals("1", employeeData.get().getId());
        Assert.assertEquals(employeeData.get().getEmployee_name(), postEmployeeRsp.getApiResponse().getData().getName());
        Assert.assertEquals(employeeData.get().getEmployee_age(), postEmployeeRsp.getApiResponse().getData().getAge());
        Assert.assertEquals(employeeData.get().getEmployee_salary(), postEmployeeRsp.getApiResponse().getData().getSalary());
    }

    @Test(groups = {Group.REGRESSION, Group.SMOKE}, dataProvider = "employee", dataProviderClass = EmployeeProvider.class)
    public void updateEmployeeDetails(Employee employee) {

        RestResponse<PostEmployeeRsp> postEmployeeRsp = employeeTestHelper.postEmployee(employee);
        logger.info("Post Employee Response  : \n" + gson.toJson(postEmployeeRsp.getApiResponse()));

        employee.setName("FindMe");
        RestResponse<EmployeeInfo> updateEmployee = employeeTestHelper.updateEmployee(1, employee);
        logger.info("Put Employee Response  : \n" + gson.toJson(updateEmployee.getApiResponse()));

        Assert.assertEquals(updateEmployee.getApiResponse().getStatus(), "success");
    }
}
