package com.atlassian.api.client.restassured;

import com.atlassian.api.entities.*;
import com.atlassian.api.exceptions.InvalidEnvironmentException;
import com.atlassian.api.property.Environment;
import com.atlassian.api.property.SystemProperties;
import com.google.inject.Inject;
import org.aeonbits.owner.ConfigFactory;

import java.util.Map;

public class RestAssuredClient {

    private IRestClient iRestClient;
    Environment testEnvironment;

    @Inject
    public RestAssuredClient(IRestClient iRestClient){
        try{
            ConfigFactory.setProperty("env", SystemProperties.ENV);
            testEnvironment = ConfigFactory.create(Environment.class);
            this.iRestClient = iRestClient;
        }
        catch (Exception e){
            throw new InvalidEnvironmentException(SystemProperties.ENV);
        }
    }


    public <T> RestResponse<PostEmployeeRsp> postEmployee(T requestObject){
        RestRequest restRequest = getRestRequest(testEnvironment.endpoint(), "/api/v1/create", requestObject);
        return iRestClient.postRequest(restRequest, PostEmployeeRsp.class);
    }

    public <T> RestResponse<EmployeeInfo> updateEmployee(Integer employeeId, T requestObject){
        RestRequest restRequest = getRestRequest(testEnvironment.endpoint(), String.format("/api/v1/update/%s", employeeId), requestObject);
        return iRestClient.putRequest(restRequest, EmployeeInfo.class);
    }

    public RestResponse<GetEmployeesRsp> getEmployeeList(){
        RestRequest restRequest = getRestRequest(testEnvironment.endpoint(), "/api/v1/employees");
        return iRestClient.getResponse(restRequest, GetEmployeesRsp.class);
    }

    public RestResponse<EmployeeInfo> getEmployee(Integer employeeId){
        RestRequest restRequest = getRestRequest(testEnvironment.endpoint(), String.format("/api/v1/employee/%s", employeeId));
        return iRestClient.getResponse(restRequest, EmployeeInfo.class);
    }

    private RestRequest getRestRequest(String endpoint, String requestUri){
        return getRestRequest(endpoint, requestUri, null, null);
    }

    private <T> RestRequest getRestRequest(String endpoint, String requestUri, T requestObject){
        return getRestRequest(endpoint, requestUri, requestObject, null);
    }

    private <T> RestRequest getRestRequest(String endpoint, String requestUri, T requestObject, Map<String, String> header){
        RestRequest restRequest = new RestRequest();
        restRequest.setEndpoint(endpoint);
        restRequest.setRequestObject(requestObject);
        restRequest.setRequestUri(requestUri);
        restRequest.setHeaderMap(header);
        return restRequest;
    }

}
