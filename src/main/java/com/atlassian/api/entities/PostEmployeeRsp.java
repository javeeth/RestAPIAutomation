package com.atlassian.api.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostEmployeeRsp {
    private String status;
    private Employee data;
}
