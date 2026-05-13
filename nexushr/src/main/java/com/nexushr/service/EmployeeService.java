package com.nexushr.service;

import com.nexushr.entity.Employee;
import com.nexushr.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.nexushr.exception.ResourceNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    public Employee getById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Employee not found"));
    }

    public Employee update(Long id, Employee employee) {

        Employee existing = getById(id);

        existing.setName(employee.getName());
        existing.setDepartment(employee.getDepartment());
        existing.setPosition(employee.getPosition());
        existing.setSalary(employee.getSalary());

        return employeeRepository.save(existing);
    }

    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }
}