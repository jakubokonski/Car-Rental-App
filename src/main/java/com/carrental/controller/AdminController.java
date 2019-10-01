package com.carrental.controller;

import com.carrental.model.Client;
import com.carrental.model.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("")
    private String admin() {
        return "admin";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }




}
