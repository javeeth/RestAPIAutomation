package com.atlassian.apitesting.tests;


import com.atlassian.api.entities.*;
import com.atlassian.api.property.SystemProperties;
import com.atlassian.apitesting.Group;
import com.atlassian.apitesting.apiHelper.AssertionHelper;
import com.atlassian.apitesting.apiHelper.RetrofitEmployeeTestHelper;
import com.atlassian.apitesting.dataProviders.EmployeeProvider;
import com.google.gson.Gson;
import com.google.inject.Inject;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.util.logging.Logger;

@Epic("Employee Information")
@Feature("Process Employee Information")
public class RetrofitEmployeeTests extends TestBase{

    @Inject
    Gson gson;
    @Inject
    Logger logger;
    @Inject
    RetrofitEmployeeTestHelper retrofitEmployeeTestHelper;
    @Inject
    AssertionHelper assertionHelper;

    @BeforeMethod
    void initializeApiContext(){
        logger.info("Test Environment is : " + SystemProperties.ENV);
    }

    @Test(groups = {Group.REGRESSION, Group.SMOKE})
    public void happyPathGetEmployeeListUsingRetrofit(){
        RestResponse<GetEmployeesRsp> getEmployeesRsp = retrofitEmployeeTestHelper.getEmployees();
        logger.info("GetEmployee Response  : \n" + gson.toJson(getEmployeesRsp.getApiResponse()));
        Assert.assertEquals(getEmployeesRsp.getStatusCode(), HttpStatus.SC_OK);
        Assert.assertEquals(getEmployeesRsp.getApiResponse().getStatus(), "success");
    }

    @Test(groups = {Group.REGRESSION, Group.SMOKE})
    public void happyPathGetEmployeeUsingRetrofit(){
        String employeeId = "1";
        RestResponse<EmployeeInfo> getEmployeesRsp = retrofitEmployeeTestHelper.getEmployee(employeeId);
        //logger.info("GetEmployee Response  : \n" + gson.toJson(getEmployeesRsp.getApiResponse()));
        //Assert.assertEquals(getEmployeesRsp.getStatusCode(), HttpStatus.SC_OK);
    }

    @Test(groups = {Group.REGRESSION, Group.SMOKE}, dataProvider = "employee", dataProviderClass = EmployeeProvider.class)
    @Story("Post Employee Verification")
    public void happyPathPostEmployee(Employee employee) {
        RestResponse<PostEmployeeRsp> postEmployeeRsp = retrofitEmployeeTestHelper.postEmployee(employee);
        Assert.assertEquals(postEmployeeRsp.getStatusCode(), HttpStatus.SC_OK);
        logger.info("Post Employee Response  : \n" + gson.toJson(postEmployeeRsp.getApiResponse()));
    }

    @Test(groups = {Group.REGRESSION, Group.SMOKE}, dataProvider = "employee", dataProviderClass = EmployeeProvider.class)
    public void happyPathCreateEmployeeAndUpdateEmployeeDetails(Employee employee) {

        RestResponse<PostEmployeeRsp> postEmployeeRsp = retrofitEmployeeTestHelper.postEmployee(employee);
        Assert.assertEquals(HttpStatus.SC_OK, postEmployeeRsp.getStatusCode());
        logger.info("Post Employee Response  : \n" + gson.toJson(postEmployeeRsp.getApiResponse()));

        assertionHelper.assertEmployeeData(postEmployeeRsp.getApiResponse(), employee);

        employee.setName("FindMe");
        RestResponse<EmployeeInfo> updateEmployee = retrofitEmployeeTestHelper.updateEmployee(1, employee);
        Assert.assertEquals(HttpStatus.SC_OK, updateEmployee.getStatusCode());
        logger.info("Put Employee Response  : \n" + gson.toJson(updateEmployee.getApiResponse()));

        Assert.assertEquals(updateEmployee.getApiResponse().getStatus(), "success");
    }

    @Test(groups = {Group.REGRESSION, Group.SMOKE}, dataProvider = "employee", dataProviderClass = EmployeeProvider.class)
    @Story("Post Employee Verification")
    public void happyPathPostEmployeeAndDeleteEmployee(Employee employee) {
        RestResponse<PostEmployeeRsp> postEmployeeRsp = retrofitEmployeeTestHelper.postEmployee(employee);
        Assert.assertEquals(postEmployeeRsp.getStatusCode(), HttpStatus.SC_OK);
        logger.info("Post Employee Response  : \n" + gson.toJson(postEmployeeRsp.getApiResponse()));

        RestResponse<DeleteEmployeeRsp> deleteEmployeeRspRestResponse = retrofitEmployeeTestHelper.deleteEmployee(postEmployeeRsp.getApiResponse().getData().getId());
        logger.info("Delete Employee Response  : \n" + gson.toJson(deleteEmployeeRspRestResponse.getApiResponse()));
        Assert.assertEquals(deleteEmployeeRspRestResponse.getApiResponse().getStatus(), "failed");
    }
}
