package com.carrental.controller;

import com.carrental.model.Employee;
import com.carrental.model.dto.EmployeeDTO;
import com.carrental.service.DepartmentService;
import com.carrental.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/register")
    private String register(Model model) {
        model.addAttribute("employee", Employee.builder().build());
        return "register";
    }

    @PostMapping("/register-owner")
    private String registerSuccess(@Valid @ModelAttribute Employee employee,
                                   BindingResult bindingResult,
                                   Model model) {
        model.addAttribute("employee", employee);

        if (bindingResult.hasErrors()) {
            return "register";
        }

        employeeService.addOwner(employee);
        return "success";
    }

    @GetMapping("/admin/add-employee")
    private String addEmployee(Model model) {
        EmployeeDTO employeeDTO = EmployeeDTO.builder().build();
        employeeDTO.setDepartments(departmentService.getAll());
        model.addAttribute("employeeDTO", employeeDTO);
        return "/admin/add-employee";
    }

    @PostMapping("/admin/add-employee")
    private String addEmployee(@Valid @ModelAttribute(name = "employeeDTO") EmployeeDTO employeeDTO,
                               BindingResult bindingResult,
                               Model model) {
//        model.addAttribute("employee", employee);
//        List<Department> departments = departmentService.getAll();
//        model.addAttribute("departments", departments);
//        employeeDTO.setDepartments(departmentService.getAll());
        if (bindingResult.hasErrors()) {
            return "/admin/add-employee";
        }
        Employee employee = Employee.builder()
                .name(employeeDTO.getName())
                .surname(employeeDTO.getSurname())
                .role(employeeDTO.getRole())
                .department(employeeDTO.getDepartment())
                .build();
        employeeService.addEmployee(employee);
        return "success";
    }
}
