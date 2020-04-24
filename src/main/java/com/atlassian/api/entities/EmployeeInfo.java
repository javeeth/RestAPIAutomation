package com.atlassian.api.entities;

public class EmployeeInfo {
    private String status;
    private EmployeeData data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public EmployeeData getData() {
        return data;
    }

    public void setData(EmployeeData data) {
        this.data = data;
    }
}
