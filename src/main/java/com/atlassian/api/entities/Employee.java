package com.atlassian.api.entities;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Employee {
    private String name;
    private String salary;
    private String age;
    private Integer id;
}