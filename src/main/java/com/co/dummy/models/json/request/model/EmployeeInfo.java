package com.co.dummy.models.json.request.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeInfo {
    private String name;
    private String salary;
    private String age;
}
