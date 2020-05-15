package com.atlassian.api.client;

import com.atlassian.api.entities.*;

public interface IRestClient {
    RestResponse<EmployeeInfo> getEmployee(String employeeId);
    RestResponse<GetEmployeesRsp> getEmployeeList();
    RestResponse<PostEmployeeRsp> postEmployee(Employee requestObject);
    RestResponse<EmployeeInfo> updateEmployee(Integer employeeId, Employee employee);
    RestResponse<DeleteEmployeeRsp> deleteEmployee(Integer employeeId);
}
