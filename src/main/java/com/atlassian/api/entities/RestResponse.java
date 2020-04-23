package com.atlassian.api.entities;

public class RestResponse<T> {

    private int statusCode;
    private T apiResponse;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public T getApiResponse() {
        return apiResponse;
    }

    public void setApiResponse(T apiResponse) {
        this.apiResponse = apiResponse;
    }
}
