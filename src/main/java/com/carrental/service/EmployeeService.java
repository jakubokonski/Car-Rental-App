package com.carrental.service;

import com.carrental.model.Employee;
import com.carrental.model.enums.Role;
import com.carrental.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public void addOwner(Employee employee) {
        Role role = Role.OWNER;
        employee.setRole(role);
        employeeRepository.save(employee);
    }

    public void addEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

}
