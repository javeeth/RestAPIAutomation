package com.atlassian.api.client.restassured;

import com.atlassian.api.entities.RestRequest;
import com.atlassian.api.entities.RestResponse;

public interface IRestClient {

    <T> RestResponse<T> postRequest(RestRequest restRequest, Class<T> responseClass);
    <T> RestResponse<T> getResponse(RestRequest restRequest, Class<T> responseClass);
    <T> RestResponse<T> putRequest(RestRequest restRequest, Class<T> responseClass);
    <T> RestResponse<T> deleteRequest(RestRequest restRequest, Class<T> responseClass);
}
