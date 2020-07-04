package com.employee.service;

import com.employee.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAllEmployees();

    Employee getEmployee(Long id);

    Long saveEmployee(Employee employee);

    void deleteEmployee(Long id);

    Employee updateEmployee(Employee employee, Long id);


}
