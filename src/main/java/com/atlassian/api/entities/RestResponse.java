package com.atlassian.api.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestResponse<T> {

    private int statusCode;
    private T apiResponse;
}
