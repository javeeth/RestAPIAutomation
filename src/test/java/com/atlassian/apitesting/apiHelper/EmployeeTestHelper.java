package com.atlassian.apitesting.apiHelper;

import com.atlassian.api.client.restassured.RestAssuredClient;
import com.atlassian.api.entities.GetEmployeesRsp;
import com.atlassian.api.entities.RestResponse;
import com.atlassian.api.property.Environment;

public class EmployeeTestHelper {

    public RestResponse<GetEmployeesRsp> getEmployeeList(Environment environment){
        RestAssuredClient restAssuredClient = new RestAssuredClient(environment);
        RestResponse<GetEmployeesRsp> apiResponse = restAssuredClient.getEmployeeList();
        return apiResponse;
    }
}
