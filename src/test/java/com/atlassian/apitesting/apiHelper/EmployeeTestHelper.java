package com.atlassian.apitesting.apiHelper;

import com.atlassian.api.client.restassured.RestAssuredClient;
import com.atlassian.api.entities.*;
import com.atlassian.api.property.Environment;
import com.google.gson.Gson;

public class EmployeeTestHelper {

    Gson gson = new Gson();
    RestAssuredClient restAssuredClient;

    public EmployeeTestHelper(Environment environment){
        this.restAssuredClient = new RestAssuredClient(environment);
    }
    public RestResponse<GetEmployeesRsp> getEmployees(){
        RestResponse<GetEmployeesRsp> apiResponse = restAssuredClient.getEmployeeList();
        return apiResponse;
    }

    public RestResponse<EmployeeInfo> getEmployee(Integer employeeId){
        RestResponse<EmployeeInfo> apiResponse = restAssuredClient.getEmployee(employeeId);
        return apiResponse;
    }

    public RestResponse<PostEmployeeRsp> postEmployee(Employee employee){
        String requestBody = gson.toJson(employee);
        RestResponse<PostEmployeeRsp> apiResponse = restAssuredClient.postEmployee(requestBody);
        return apiResponse;
    }

    public RestResponse<EmployeeInfo> updateEmployee(Integer employeeId, Employee employee){
        String requestBody = gson.toJson(employee);
        RestResponse<EmployeeInfo> apiResponse = restAssuredClient.updateEmployee(employeeId, requestBody);
        return apiResponse;
    }
}
