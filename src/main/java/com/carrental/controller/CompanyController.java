package com.carrental.controller;

import com.carrental.model.Company;
import com.carrental.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    // Refactor to EDIT company details
    @GetMapping("/create-company")
    private String createComapny(Model model) {
        model.addAttribute("company", Company.builder().build());
        return "create-company";
    }

    @PostMapping("/create-company")
    private String registerCompany(Company company, Model model) {
        model.addAttribute("company", company);
        companyService.saveCompany(company);
        return "success";
    }
}
