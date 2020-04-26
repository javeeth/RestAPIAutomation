package com.atlassian.apitesting.tests;

import com.atlassian.api.entities.*;
import com.atlassian.api.exceptions.InvalidEnvironmentException;
import com.atlassian.api.property.Environment;
import com.atlassian.api.property.SystemProperties;
import com.atlassian.apitesting.Group;
import com.atlassian.apitesting.apiHelper.AssertionHelper;
import com.atlassian.apitesting.apiHelper.EmployeeTestHelper;
import com.atlassian.apitesting.dataProviders.EmployeeProvider;
import com.google.gson.Gson;
import org.aeonbits.owner.ConfigFactory;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Optional;
import java.util.logging.Logger;

public class EmployeeTests {
    
    EmployeeTestHelper employeeTestHelper;
    Gson gson;
    AssertionHelper assertionHelper;
    Logger logger = Logger.getLogger(EmployeeTests.class.getName());

    @BeforeMethod
    void initializeApiContext(){
        logger.info("Test Environment is : " + SystemProperties.ENV);
        employeeTestHelper = new EmployeeTestHelper();
        assertionHelper = new AssertionHelper();
        gson = new Gson();
    }

    @Test(groups = {Group.REGRESSION, Group.SMOKE})
    public void happyPathGetEmployeeList(){
        RestResponse<GetEmployeesRsp> getEmployeesRsp = employeeTestHelper.getEmployees();
        logger.info("GetEmployee Response  : \n" + gson.toJson(getEmployeesRsp.getApiResponse()));
        Assert.assertEquals(HttpStatus.SC_OK, getEmployeesRsp.getStatusCode());
    }

    @Test(groups = {Group.REGRESSION, Group.SMOKE}, dataProvider = "employee", dataProviderClass = EmployeeProvider.class)
    public void happyPathPostEmployeeAndVerifyEmployeeDetails(Employee employee){

        RestResponse<PostEmployeeRsp> postEmployeeRsp = employeeTestHelper.postEmployee(employee);
        Assert.assertEquals(HttpStatus.SC_OK, postEmployeeRsp.getStatusCode());
        logger.info("GetEmployee Response  : \n" + gson.toJson(postEmployeeRsp.getApiResponse()));

        assertionHelper.assertEmployeeData(postEmployeeRsp.getApiResponse(), employee);

        RestResponse<GetEmployeesRsp> getEmployeesRsp = employeeTestHelper.getEmployees();
        Assert.assertEquals(HttpStatus.SC_OK, getEmployeesRsp.getStatusCode());
        logger.info("GetEmployee Response  : \n" + gson.toJson(getEmployeesRsp.getApiResponse()));

        Optional<EmployeeData> employeeData =  getEmployeesRsp.getApiResponse().getData().stream().filter(i -> i.getId().equals("1")).findAny();

        assertionHelper.assertEmployeeData(employeeData.get(), postEmployeeRsp.getApiResponse());
    }

    @Test(groups = {Group.REGRESSION, Group.SMOKE}, dataProvider = "employee", dataProviderClass = EmployeeProvider.class)
    public void happyPathCreateEmployeeAndUpdateEmployeeDetails(Employee employee) {

        RestResponse<PostEmployeeRsp> postEmployeeRsp = employeeTestHelper.postEmployee(employee);
        Assert.assertEquals(HttpStatus.SC_OK, postEmployeeRsp.getStatusCode());
        logger.info("Post Employee Response  : \n" + gson.toJson(postEmployeeRsp.getApiResponse()));

        assertionHelper.assertEmployeeData(postEmployeeRsp.getApiResponse(), employee);

        employee.setName("FindMe");
        RestResponse<EmployeeInfo> updateEmployee = employeeTestHelper.updateEmployee(1, employee);
        Assert.assertEquals(HttpStatus.SC_OK, updateEmployee.getStatusCode());
        logger.info("Put Employee Response  : \n" + gson.toJson(updateEmployee.getApiResponse()));

        Assert.assertEquals(updateEmployee.getApiResponse().getStatus(), "success");
    }
}
