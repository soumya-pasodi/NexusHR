package com.nexushr.service.impl;

import com.nexushr.dto.request.DepartmentRequest;
import com.nexushr.dto.response.DepartmentResponse;
import com.nexushr.entity.Department;
import com.nexushr.entity.Employee;
import com.nexushr.repository.DepartmentRepository;
import com.nexushr.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Override
    public DepartmentResponse createDepartment(DepartmentRequest request) {
        if (departmentRepository.findByNameIgnoreCase(request.getName()).isPresent()) {
            throw new RuntimeException("Department with this name already exists");
        }
        Department department = new Department();
        department.setName(request.getName());
        department.setDescription(request.getDescription());
        Department savedDept = departmentRepository.save(department);
        return mapToResponse(savedDept);
    }

    @Override
    public List<DepartmentResponse> getAllDepartments() {
        return departmentRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentResponse getDepartmentById(Long id) {
        Department department = departmentRepository.findByIdWithEmployees(id)
                .orElseThrow(() -> new RuntimeException("Department with id " + id + " not found"));
        return mapToResponse(department);
    }

    @Override
    public DepartmentResponse updateDepartment(Long id, DepartmentRequest request) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department with id " + id + " not found"));
        department.setName(request.getName());
        department.setDescription(request.getDescription());
        Department savedDepartment = departmentRepository.save(department);
        return mapToResponse(savedDepartment);
    }

    @Override
    public void deleteDepartment(Long id) {
        if(!departmentRepository.existsById(id)) {
            throw new RuntimeException("Department with id " + id + " not found");
        }
        departmentRepository.deleteById(id);
    }

    private DepartmentResponse mapToResponse(Department department) {
        List<Employee> employees = List.of();
        if (department.getEmployees() != null) {
            employees = department.getEmployees().stream()
                    .map(emp->{
                        Employee employee = new Employee();
                        employee.setId(emp.getId());
                        employee.setName(emp.getName());
                        employee.setSalary(emp.getSalary());
                        employee.setDepartment(emp.getDepartment());
                        return employee;
                    }).collect(Collectors.toList());
        }
        DepartmentResponse departmentResponse = new DepartmentResponse();
        departmentResponse.setId(department.getId());
        departmentResponse.setName(department.getName());
        departmentResponse.setDescription(department.getDescription());
        departmentResponse.setEmployees(employees);
        return departmentResponse;
    }
}
