package com.atlassian.apitesting.apiHelper;

import com.atlassian.api.client.IRestClient;
import com.atlassian.api.client.restassured.RestAssuredClient;
import com.atlassian.api.client.restassured.RestAssuredClientBase;
import com.atlassian.api.entities.*;
import com.atlassian.api.exceptions.InvalidEnvironmentException;
import com.atlassian.api.property.Environment;
import com.atlassian.api.property.SystemProperties;
import com.google.inject.Inject;
import org.aeonbits.owner.ConfigFactory;

public class EmployeeTestHelper {

    @Inject
    IRestClient iRestClient;

    @Inject
    public EmployeeTestHelper(IRestClient iRestClient){
        this.iRestClient = iRestClient;
    }

    public RestResponse<GetEmployeesRsp> getEmployees(){
        RestResponse<GetEmployeesRsp> apiResponse = iRestClient.getEmployeeList();
        return apiResponse;
    }

    public RestResponse<EmployeeInfo> getEmployee(String employeeId){
        RestResponse<EmployeeInfo> apiResponse = iRestClient.getEmployee(employeeId);
        return apiResponse;
    }

    public RestResponse<PostEmployeeRsp> postEmployee(Employee employee){
        RestResponse<PostEmployeeRsp> apiResponse = iRestClient.postEmployee(employee);
        return apiResponse;
    }

    public RestResponse<EmployeeInfo> updateEmployee(Integer employeeId, Employee employee){
        RestResponse<EmployeeInfo> apiResponse = iRestClient.updateEmployee(employeeId, employee);
        return apiResponse;
    }
}
