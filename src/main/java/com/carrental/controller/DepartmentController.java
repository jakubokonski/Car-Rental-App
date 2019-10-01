package com.carrental.controller;

import com.carrental.model.Address;
import com.carrental.model.Department;
import com.carrental.model.dto.DepartmentForm;
import com.carrental.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class DepartmentController {

    private DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/add-department")
    private String addDepartment(Model model) {
        model.addAttribute("departmentForm", DepartmentForm.builder().build());
        return "add-department";
    }

    @PostMapping("/add-department")
    private String addDepartment(@Valid @ModelAttribute DepartmentForm departmentForm,
                                 BindingResult bindingResult,
                                 Model model) {
        model.addAttribute("departmentForm", departmentForm);
        if (bindingResult.hasErrors()) {
            return "add-department";
        }
        Address address = Address.builder()
                .street(departmentForm.getStreet())
                .buildingNumber(departmentForm.getBuildingNumber())
                .city(departmentForm.getCity())
                .build();

        Department department = Department.builder()
                .name(departmentForm.getName())
                .address(address)
                .build();

        departmentService.addDepartment(department);
        return "success";
    }
}