package com.carrental.controller;

import com.carrental.model.Address;
import com.carrental.model.Company;
import com.carrental.model.Department;
import com.carrental.model.Employee;
import com.carrental.model.dto.GettingStartedForm;
import com.carrental.model.enums.Role;
import com.carrental.service.CompanyService;
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
public class GettingStartedController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/getting-started")
    private String gettingStarted(Model model) {
        model.addAttribute("gettingStartedForm", GettingStartedForm.builder().build());
        return "getting-started";
    }

    @PostMapping("/getting-started")
    private String gettingStarted(@Valid @ModelAttribute GettingStartedForm gettingStartedForm,
                                  BindingResult bindingResult,
                                  Model model) {
        model.addAttribute("gettingStartedForm", gettingStartedForm);

        if (bindingResult.hasErrors()) {
            return "getting-started";
        }

        Address address = Address.builder()
                .street(gettingStartedForm.getStreet())
                .buildingNumber(gettingStartedForm.getBuildingNumber())
                .city(gettingStartedForm.getCity())
                .build();

        Department department = Department.builder()
                .name(gettingStartedForm.getName())
                .address(address)
                .build();

        Employee employee = Employee.builder()
                .name(gettingStartedForm.getName())
                .surname(gettingStartedForm.getSurname())
                .role(Role.OWNER)
                .department(department)
                .build();

        Company company = Company.builder()
                .companyName(gettingStartedForm.getCompanyName())
                .domain(gettingStartedForm.getDomain())
                .address(address)
                .owner(employee)
                .build();

        departmentService.addDepartment(department);
        employeeService.addEmployee(employee);
        companyService.saveCompany(company);

        return "success";
    }
}
