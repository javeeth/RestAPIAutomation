package com.atlassian.api.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetEmployeesRsp {

    private String status;
    private List<EmployeeData> data;
}
