package com.atlassian.apitesting.apiHelper;

import com.atlassian.api.client.retrofit.RetrofitClientBase;
import com.atlassian.api.entities.*;
import com.google.inject.Inject;

public class RetrofitEmployeeTestHelper {

    @Inject
    RetrofitClientBase retrofitClientBase;

    public RestResponse<GetEmployeesRsp> getEmployees(){
        RestResponse<GetEmployeesRsp> apiResponse = retrofitClientBase.getEmployeeList();
        return apiResponse;
    }

    public RestResponse<EmployeeInfo> getEmployee(String id){
        RestResponse<EmployeeInfo> apiResponse = retrofitClientBase.getEmployee(id);
        return apiResponse;
    }

    public RestResponse<PostEmployeeRsp> postEmployee(Employee employee){
        RestResponse<PostEmployeeRsp> apiResponse = retrofitClientBase.postEmployee(employee);
        return apiResponse;
    }

    public RestResponse<EmployeeInfo> updateEmployee(Integer employeeId, Employee employee){
        RestResponse<EmployeeInfo> apiResponse = retrofitClientBase.updateEmployee(employeeId, employee);
        return apiResponse;
    }

    public RestResponse<DeleteEmployeeRsp> deleteEmployee(Integer employeeId){
        RestResponse<DeleteEmployeeRsp> apiResponse = retrofitClientBase.deleteEmployee(employeeId);
        return apiResponse;
    }
}
