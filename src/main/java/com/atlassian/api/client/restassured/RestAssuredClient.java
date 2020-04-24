package com.atlassian.api.client.restassured;

import com.atlassian.api.entities.*;
import com.atlassian.api.property.Environment;
import com.google.gson.Gson;
import io.restassured.response.Response;
import java.util.Map;

public class RestAssuredClient {

    public String endpoint;
    BaseClient baseClient = new BaseClient();
    Gson gson = new Gson();

    public RestAssuredClient(Environment env){
        this.endpoint = env.endpoint();
    }


    public RestResponse<PostEmployeeRsp> postEmployee(String requestBody){
        RestRequest restRequest = getRestRequest(endpoint, "/api/v1/create", requestBody);
        Response response = baseClient.postRequest(restRequest);
        RestResponse<PostEmployeeRsp> apiResponse = mapResponse(PostEmployeeRsp.class, response);
        return apiResponse;
    }

    public RestResponse<EmployeeInfo> updateEmployee(Integer employeeId, String requestBody){
        RestRequest restRequest = getRestRequest(endpoint, String.format("/api/v1/update/%s", employeeId), requestBody);
        Response response = baseClient.putRequest(restRequest);
        RestResponse<EmployeeInfo> apiResponse = mapResponse(EmployeeInfo.class, response);
        return apiResponse;
    }

    public RestResponse<GetEmployeesRsp> getEmployeeList(){
        RestRequest restRequest = getRestRequest(endpoint, "/api/v1/employees");
        Response response = baseClient.getResponse(restRequest);
        RestResponse<GetEmployeesRsp> apiResponse = mapResponse(GetEmployeesRsp.class, response);
        return apiResponse;
    }

    public RestResponse<EmployeeInfo> getEmployee(Integer employeeId){
        RestRequest restRequest = getRestRequest(endpoint, String.format("/api/v1/employee/%s", employeeId));
        Response response = baseClient.getResponse(restRequest);
        RestResponse<EmployeeInfo> apiResponse = mapResponse(EmployeeInfo.class, response);
        return apiResponse;
    }

    private RestRequest getRestRequest(String endpoint, String requestUri){
        return getRestRequest(endpoint, requestUri, null, null);
    }

    private RestRequest getRestRequest(String endpoint, String requestUri, String requestBody){
        return getRestRequest(endpoint, requestUri, requestBody, null);
    }

    private RestRequest getRestRequest(String endpoint, String requestUri, String requestBody, Map<String, String> header){
        RestRequest restRequest = new RestRequest();
        restRequest.setEndpoint(endpoint);
        restRequest.setRequestBody(requestBody);
        restRequest.setRequestUri(requestUri);
        restRequest.setHeaderMap(header);
        return restRequest;
    }

    private <T> RestResponse<T> mapResponse(Class<T> responseClass, Response response) {
        RestResponse<T> apiResponse = new RestResponse<>();
        T data = gson.fromJson(response.asString(), responseClass);
        apiResponse.setApiResponse(data);
        apiResponse.setStatusCode(response.statusCode());
        return apiResponse;
    }

}
