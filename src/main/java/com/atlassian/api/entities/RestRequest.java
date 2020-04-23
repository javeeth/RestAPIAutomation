package com.atlassian.api.entities;

import java.util.Map;

public class RestRequest {
    private String requestBody;
    private Map<String,String> headerMap;
    private String endpoint;
    private String requestUri;

    public String getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    public Map<String, String> getHeaderMap() {
        return headerMap;
    }

    public void setHeaderMap(Map<String, String> headerMap) {
        this.headerMap = headerMap;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getRequestUri() {
        return requestUri;
    }

    public void setRequestUri(String requestUri) {
        this.requestUri = requestUri;
    }
}
