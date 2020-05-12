package com.atlassian.api.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class RestRequest<T> {
    private T requestObject;
    private Map<String,String> headerMap;
    private String endpoint;
    private String requestUri;
}
