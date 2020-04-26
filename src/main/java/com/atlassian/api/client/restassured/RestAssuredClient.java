package com.atlassian.api.client.restassured;

import com.atlassian.api.entities.*;
import com.atlassian.api.exceptions.InvalidEnvironmentException;
import com.atlassian.api.property.Environment;
import com.atlassian.api.property.SystemProperties;
import io.restassured.response.Response;
import org.aeonbits.owner.ConfigFactory;

import java.util.Map;

public class RestAssuredClient extends BaseClient{

    Environment testEnvironment;

    public RestAssuredClient(){
        try{
            ConfigFactory.setProperty("env", SystemProperties.ENV);
            testEnvironment = ConfigFactory.create(Environment.class);
        }
        catch (Exception e){
            throw new InvalidEnvironmentException(SystemProperties.ENV);
        }
    }


    public RestResponse<PostEmployeeRsp> postEmployee(Object requestBody){
        RestRequest restRequest = getRestRequest(testEnvironment.endpoint(), "/api/v1/create", requestBody);
        Response response = postRequest(restRequest);
        RestResponse<PostEmployeeRsp> apiResponse = mapResponse(PostEmployeeRsp.class, response);
        return apiResponse;
    }

    public RestResponse<EmployeeInfo> updateEmployee(Integer employeeId, Object requestBody){
        RestRequest restRequest = getRestRequest(testEnvironment.endpoint(), String.format("/api/v1/update/%s", employeeId), requestBody);
        Response response = putRequest(restRequest);
        RestResponse<EmployeeInfo> apiResponse = mapResponse(EmployeeInfo.class, response);
        return apiResponse;
    }

    public RestResponse<GetEmployeesRsp> getEmployeeList(){
        RestRequest restRequest = getRestRequest(testEnvironment.endpoint(), "/api/v1/employees");
        Response response = getResponse(restRequest);
        RestResponse<GetEmployeesRsp> apiResponse = mapResponse(GetEmployeesRsp.class, response);
        return apiResponse;
    }

    public RestResponse<EmployeeInfo> getEmployee(Integer employeeId){
        RestRequest restRequest = getRestRequest(testEnvironment.endpoint(), String.format("/api/v1/employee/%s", employeeId));
        Response response = getResponse(restRequest);
        RestResponse<EmployeeInfo> apiResponse = mapResponse(EmployeeInfo.class, response);
        return apiResponse;
    }

    private RestRequest getRestRequest(String endpoint, String requestUri){
        return getRestRequest(endpoint, requestUri, null, null);
    }

    private RestRequest getRestRequest(String endpoint, String requestUri, Object requestBody){
        return getRestRequest(endpoint, requestUri, requestBody, null);
    }

    private RestRequest getRestRequest(String endpoint, String requestUri, Object requestBody, Map<String, String> header){
        RestRequest restRequest = new RestRequest();
        restRequest.setEndpoint(endpoint);
        restRequest.setRequestBody(requestBody);
        restRequest.setRequestUri(requestUri);
        restRequest.setHeaderMap(header);
        return restRequest;
    }

    private <T> RestResponse<T> mapResponse(Class<T> responseClass, Response response) {
        RestResponse<T> apiResponse = new RestResponse<>();
        apiResponse.setApiResponse(response.as(responseClass));
        apiResponse.setStatusCode(response.statusCode());
        return apiResponse;
    }

}
