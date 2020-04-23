package com.atlassian.api.client.restassured;

import com.atlassian.api.entities.RestRequest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BaseClient {

    public Response postRequest(RestRequest restRequest){

        RestAssured.baseURI = restRequest.getEndpoint();
        RestAssured.basePath = restRequest.getRequestUri();

        Response response =  given().contentType(ContentType.JSON)
                .body(restRequest.getRequestBody())
                .post()
                .then()
                .extract()
                .response();

        return response;

    }

    public Response getResponse(RestRequest restRequest){

        RestAssured.baseURI = restRequest.getEndpoint();
        RestAssured.basePath = restRequest.getRequestUri();

        Response response =  given()
                .get()
                .then()
                .extract()
                .response();

        return response;

    }
}
