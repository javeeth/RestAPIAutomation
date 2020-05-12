package com.atlassian.api.client.restassured;

import com.atlassian.api.entities.PostEmployeeRsp;
import com.atlassian.api.entities.RestRequest;
import com.atlassian.api.entities.RestResponse;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.lang.reflect.Type;

import static io.restassured.RestAssured.given;

public class BaseClient implements IRestClient {

    public <T> RestResponse<T> postRequest(RestRequest restRequest, Class<T> responseClass){

        RestAssured.baseURI = restRequest.getEndpoint();
        RestAssured.basePath = restRequest.getRequestUri();
        Response response =  given().contentType(ContentType.JSON)
                .filter(new AllureRestAssured())
                .body(restRequest.getRequestObject())
                .post()
                .then()
                .extract()
                .response();

        return mapResponse(responseClass, response);
    }

    public <T> RestResponse<T> getResponse(RestRequest restRequest, Class<T> responseClass){

        RestAssured.baseURI = restRequest.getEndpoint();
        RestAssured.basePath = restRequest.getRequestUri();

        Response response =  given()
                .filter(new AllureRestAssured())
                .get()
                .then()
                .extract()
                .response();

        return mapResponse(responseClass, response);
    }

    public <T> RestResponse<T> putRequest(RestRequest restRequest, Class<T> responseClass){

        RestAssured.baseURI = restRequest.getEndpoint();
        RestAssured.basePath = restRequest.getRequestUri();

        Response response =  given().contentType(ContentType.JSON)
                .filter(new AllureRestAssured())
                .body(restRequest.getRequestObject())
                .put()
                .then()
                .extract()
                .response();

        return mapResponse(responseClass, response);
    }

    public <T> RestResponse<T> deleteRequest(RestRequest restRequest, Class<T> responseClass){

        RestAssured.baseURI = restRequest.getEndpoint();
        RestAssured.basePath = restRequest.getRequestUri();

        Response response =  given()
                .filter(new AllureRestAssured())
                .delete()
                .then()
                .extract()
                .response();

        return mapResponse(responseClass, response);
    }

    private <T> RestResponse<T> mapResponse(Class<T> responseClass, Response response) {
        RestResponse<T> apiResponse = new RestResponse<>();
        apiResponse.setApiResponse(response.as(responseClass));
        apiResponse.setStatusCode(response.statusCode());
        return apiResponse;
    }
}
