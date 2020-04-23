package com.atlassian.apitesting.tests;

import com.atlassian.api.client.restassured.RestAssuredClient;
import com.atlassian.api.entities.GetEmployeesRsp;
import com.atlassian.api.entities.RestResponse;
import com.atlassian.api.propertyHandler.Environment;
import com.atlassian.apitesting.Group;
import org.aeonbits.owner.ConfigFactory;
import org.testng.annotations.Test;

public class EmployeeTests {

    @Test(groups = {Group.REGRESSION, Group.SMOKE})
    public void getEmployeeDetails(){
        ConfigFactory.setProperty("env", "staging");
        Environment testEnvironment = ConfigFactory.create(Environment.class);
        String endpoint = testEnvironment.endpoint();
        System.out.println(endpoint);
        RestAssuredClient restAssuredClient = new RestAssuredClient();
        RestResponse<GetEmployeesRsp> apiResponse = restAssuredClient.getEmployeeList("http://dummy.restapiexample.com");
    }
}
