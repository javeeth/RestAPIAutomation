package com.atlassian.apitesting.tests;

import com.atlassian.api.client.restassured.RestAssuredClient;
import com.atlassian.api.entities.GetEmployeesRsp;
import com.atlassian.api.entities.RestResponse;
import com.atlassian.api.propertyHandler.Environment;
import com.atlassian.apitesting.Group;
import org.aeonbits.owner.ConfigFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class EmployeeTests {

    Environment testEnvironment;

    @BeforeMethod
    void initializeApiContext(String envs){
        ConfigFactory.setProperty("env", envs);
        testEnvironment = ConfigFactory.create(Environment.class);
    }

    @Test(groups = {Group.REGRESSION, Group.SMOKE})
    public void getEmployeeDetails(){
        RestAssuredClient restAssuredClient = new RestAssuredClient();
        RestResponse<GetEmployeesRsp> apiResponse = restAssuredClient.getEmployeeList("http://dummy.restapiexample.com");
    }
}
