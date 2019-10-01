package com.carrental.controller;

import com.carrental.model.Car;
import com.carrental.model.dto.BookingForm;
import com.carrental.service.CarBookingDatesService;
import com.carrental.service.DepartmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
public class MainController {

    private DepartmentService departmentService;
    private CarBookingDatesService carBookingDatesService;

    public MainController(DepartmentService departmentService, CarBookingDatesService carBookingDatesService) {
        this.departmentService = departmentService;
        this.carBookingDatesService = carBookingDatesService;
    }

    @GetMapping("/")
    private String home(Model model) {
        BookingForm bookingForm = BookingForm.builder().build();
        bookingForm.setDepartmentList(departmentService.getAll());
        model.addAttribute("bookingForm", bookingForm);
        return "index";
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
