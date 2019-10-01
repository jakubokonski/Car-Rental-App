package com.carrental.controller;

import com.carrental.model.Address;
import com.carrental.model.Car;
import com.carrental.model.Client;
import com.carrental.model.dto.BookingForm;
import com.carrental.model.dto.RegisterDTO;
import com.carrental.service.CarBookingDatesService;
import com.carrental.service.ClientService;
import com.carrental.service.DepartmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Controller
public class MainController {

    private DepartmentService departmentService;
    private CarBookingDatesService carBookingDatesService;
    private ClientService clientService;

    public MainController(DepartmentService departmentService, CarBookingDatesService carBookingDatesService, ClientService clientService) {
        this.departmentService = departmentService;
        this.carBookingDatesService = carBookingDatesService;
        this.clientService = clientService;
    }

    @GetMapping("/")
    private String home(Model model) {
        BookingForm bookingForm = BookingForm.builder().build();
        bookingForm.setDepartmentList(departmentService.getAll());
        model.addAttribute("bookingForm", bookingForm);
        return "index";
    }

    @GetMapping("/register")
    private String register(Model model) {
        model.addAttribute("registerDTO", RegisterDTO.builder().build());
        return "register";
    }

    @PostMapping("/register")
    private String register(@Valid @ModelAttribute RegisterDTO registerDTO,
                            BindingResult bindingResult,
                            Model model) {
        model.addAttribute("registerDTO", registerDTO);
        if (bindingResult.hasErrors()) {
            return "register";
        }
        if (clientService.isEmailInUse(registerDTO.getEmail())) {
            return "register";
        }
        Address address = Address.builder()
                .street(registerDTO.getStreet())
                .buildingNumber(registerDTO.getBuildingNumber())
                .flatNumber(registerDTO.getFlatNumber())
                .city(registerDTO.getCity())
                .build();
        Client client = Client.builder()
                .name(registerDTO.getName())
                .surname(registerDTO.getSurname())
                .email(registerDTO.getEmail())
                .password(registerDTO.getPassword())
                .address(address)
                .build();
        clientService.addClient(client);
        return "redirect:/";
    }

    @PostMapping("/car-list")
    public String find(@ModelAttribute BookingForm bookingForm, Model model) {
        LocalDate startingRental = LocalDate.parse(bookingForm.getRentalStart());
        LocalDate endingRental = LocalDate.parse(bookingForm.getRentalEnd());
        bookingForm.setDepartmentList(departmentService.getAll());
        List<Car> cars = carBookingDatesService.findCarByFreeData(startingRental, endingRental, bookingForm.getDepartmentStart());
        model.addAttribute("cars", cars);
        return "car-list";
    }
}
