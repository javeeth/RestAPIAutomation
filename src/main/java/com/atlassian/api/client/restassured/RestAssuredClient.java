package com.atlassian.api.client.restassured;

import com.atlassian.api.entities.GetEmployeesRsp;
import com.atlassian.api.entities.RestResponse;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RestAssuredClient {

    Gson gson = new Gson();

    public RestResponse<GetEmployeesRsp> postEmployeeList(String endpoint, String requestBody){
        RestAssured.baseURI = "http://dummy.restapiexample.com";
        Response response =  given().contentType(ContentType.JSON)
                .body(requestBody)
                .post("/api/v1/employees")
                .then()
                .extract()
                .response();

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
}
