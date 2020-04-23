package com.atlassian.api.client.restassured;

import com.atlassian.api.entities.GetEmployeesRsp;
import com.atlassian.api.entities.RestRequest;
import com.atlassian.api.entities.RestResponse;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class RestAssuredClient {

    BaseClient baseClient = new BaseClient();
    Gson gson = new Gson();

    public RestResponse<GetEmployeesRsp> postEmployeeList(String endpoint, String requestBody){

        RestRequest restRequest = getRestRequest(endpoint, "/api/v1/employees", requestBody);
        Response response = baseClient.getResponse(restRequest);
        GetEmployeesRsp empObject = gson.fromJson(response.toString(), GetEmployeesRsp.class);

        RestResponse<GetEmployeesRsp> apiResponse = new RestResponse<>();
        apiResponse.setApiResponse(empObject);
        apiResponse.setStatusCode(response.statusCode());

        return apiResponse;
    }

    public RestResponse<GetEmployeesRsp> getEmployeeList(String endpoint){
        RestAssured.baseURI = endpoint;
        RestAssured.basePath = "/api/v1/employees";
        Response response =  given().contentType(ContentType.JSON)
                .get()
                .then()
                .extract()
                .response();

        GetEmployeesRsp empObject = gson.fromJson(response.asString(), GetEmployeesRsp.class);

        RestResponse<GetEmployeesRsp> apiResponse = new RestResponse<>();
        apiResponse.setApiResponse(empObject);
        apiResponse.setStatusCode(response.statusCode());

        return apiResponse;
    }

    private RestRequest getRestRequest(String endpoint, String requestUri, String requestBody, Map<String, String> header){
        RestRequest restRequest = new RestRequest();
        restRequest.setEndpoint(endpoint);
        restRequest.setRequestBody(requestBody);
        restRequest.setRequestUri(requestUri);
        restRequest.setHeaderMap(header);

        return restRequest;
    }

    private RestRequest getRestRequest(String endpoint, String requestUri, String requestBody){
        return getRestRequest(endpoint, requestUri, requestBody, null);
    }
}
