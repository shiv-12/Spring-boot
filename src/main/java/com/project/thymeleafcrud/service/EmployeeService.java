package com.project.thymeleafcrud.service;


import com.project.thymeleafcrud.dao.EmployeeRepository;
import com.project.thymeleafcrud.entity.Employee;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // Here all the kind of business logic and transaction management will be happened

    // Retrieve Data
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(int empId) {
        Optional<Employee> result = employeeRepository.findById(empId);
        Employee employee;
        if (result.isPresent())
            employee = result.get();
        else
            throw new RuntimeException("Invalid Employee ID!");

        return employee;
    }

    // Insert / Update Data
    @Transactional
    public void save(Employee employee) {
        // if employee.id == 0, then it will insert the data, otherwise it will update the data
        employeeRepository.save(employee);
    }

    // Delete Data
    @Transactional
    public void deleteEmployee(int empId) {
        employeeRepository.deleteById(empId);
    }
}
