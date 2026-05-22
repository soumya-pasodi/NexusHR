package com.nexushr.dto.response;

import com.nexushr.entity.Employee;
import lombok.Data;

import java.util.List;

@Data
public class DepartmentResponse     {
    private Long id;
    private String name;
    private String description;
    private List<Employee> employees;
}
