package com.atlassian.api.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeInfo {
    private String status;
    private EmployeeData data;
}
