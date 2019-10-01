package com.carrental.controller;


import com.carrental.model.Address;
import com.carrental.model.Client;
import com.carrental.model.dto.ClientForm;
import com.carrental.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class ClientController {

    @Autowired
    private ClientService clientService;


    @GetMapping("register-client")
    public String viewRegisterClient(Model model) {
        ClientForm clientForm = new ClientForm();
        model.addAttribute("clientForm", clientForm);

        return "register-client";
    }

    @PostMapping("register-client")
    public String adClient(@Valid @ModelAttribute(name = "clientForm") ClientForm clientForm,
                           BindingResult bindingResult,
                           Model model
                           ) {
        model.addAttribute("clientForm", clientForm);

        if (bindingResult.hasErrors()) {
            return "register-client";
        }

        if (clientService.isEmailInUse(clientForm.getEmail())) {
            return "register-client";
        }

        Address address = Address.builder()
                .street(clientForm.getStreet())
                .buildingNumber(clientForm.getBuildingNumber())
                .city(clientForm.getCity())
                .build();

        Client client = Client.builder()
                .name(clientForm.getName())
                .surname(clientForm.getSurname())
                .email(clientForm.getEmail())
                .password(clientForm.getPassword())
                .address(address)
                .build();
        clientService.addClient(client);
        return "success";
    }


}
