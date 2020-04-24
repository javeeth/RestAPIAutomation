package com.atlassian.apitesting.tests;

import com.atlassian.api.client.restassured.RestAssuredClient;
import com.atlassian.api.entities.GetEmployeesRsp;
import com.atlassian.api.entities.RestResponse;
import com.atlassian.api.property.Environment;
import com.atlassian.api.property.SystemProperties;
import com.atlassian.apitesting.Group;
import com.atlassian.apitesting.apiHelper.EmployeeTestHelper;
import org.aeonbits.owner.ConfigFactory;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class EmployeeTests {

    Environment testEnvironment;
    EmployeeTestHelper employeeTestHelper = new EmployeeTestHelper();

    @BeforeMethod
    void initializeApiContext(){
        ConfigFactory.setProperty("env", SystemProperties.ENV);
        testEnvironment = ConfigFactory.create(Environment.class);
    }

    @Test(groups = {Group.REGRESSION, Group.SMOKE})
    public void getEmployeeDetails(){
        RestResponse<GetEmployeesRsp> apiResponse = employeeTestHelper.getEmployeeList(testEnvironment);
        Assert.assertEquals(HttpStatus.SC_OK, apiResponse.getStatusCode());
    }
}
