package com.atlassian.apitesting.apiHelper;

import com.atlassian.api.entities.Employee;
import com.atlassian.api.entities.EmployeeData;
import com.atlassian.api.entities.PostEmployeeRsp;
import org.testng.Assert;

public class AssertionHelper {

    public void assertEmployeeData(PostEmployeeRsp postEmployeeRsp, Employee employee){

        Assert.assertEquals(postEmployeeRsp.getData().getName(), employee.getName());
        Assert.assertEquals(postEmployeeRsp.getData().getAge(), employee.getAge());
        Assert.assertEquals(postEmployeeRsp.getData().getSalary(), employee.getSalary());
    }

    public void assertEmployeeData(EmployeeData employeeData, PostEmployeeRsp postEmployeeRsp){
        Assert.assertEquals("1", employeeData.getId());
        Assert.assertEquals(employeeData.getEmployee_name(), postEmployeeRsp.getData().getName());
        Assert.assertEquals(employeeData.getEmployee_age(), postEmployeeRsp.getData().getAge());
        Assert.assertEquals(employeeData.getEmployee_salary(), postEmployeeRsp.getData().getSalary());
    }
}
