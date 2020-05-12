package com.atlassian.apitesting.apiHelper;

import com.atlassian.api.client.restassured.RestAssuredClient;
import com.atlassian.api.entities.*;
import com.google.inject.Inject;

public class EmployeeTestHelper {

    @Inject
    RestAssuredClient restAssuredClient;

    public RestResponse<GetEmployeesRsp> getEmployees(){
        RestResponse<GetEmployeesRsp> apiResponse = restAssuredClient.getEmployeeList();
        return apiResponse;
    }

    public RestResponse<EmployeeInfo> getEmployee(Integer employeeId){
        RestResponse<EmployeeInfo> apiResponse = restAssuredClient.getEmployee(employeeId);
        return apiResponse;
    }

    public RestResponse<PostEmployeeRsp> postEmployee(Employee employee){
        RestResponse<PostEmployeeRsp> apiResponse = restAssuredClient.postEmployee(employee);
        return apiResponse;
    }

    public RestResponse<EmployeeInfo> updateEmployee(Integer employeeId, Employee employee){
        RestResponse<EmployeeInfo> apiResponse = restAssuredClient.updateEmployee(employeeId, employee);
        return apiResponse;
    }
}
