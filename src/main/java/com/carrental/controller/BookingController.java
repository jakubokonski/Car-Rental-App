package com.carrental.controller;

import com.carrental.model.Booking;
import com.carrental.model.Car;
import com.carrental.model.dto.BookingForm;
import com.carrental.model.enums.RentalStatus;
import com.carrental.service.BookingService;
import com.carrental.service.CarBookingDatesService;
import com.carrental.service.CarService;
import com.carrental.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class BookingController {

//    private BookingService bookingService;
    private DepartmentService departmentService;
    private CarService carService;
    private CarBookingDatesService carBookingDatesService;

    public BookingController(DepartmentService departmentService, CarService carService, CarBookingDatesService carBookingDatesService) {
        this.departmentService = departmentService;
        this.carService = carService;
        this.carBookingDatesService = carBookingDatesService;
    }

    @GetMapping("booking")
    public String getBooking(Model model) {
        BookingForm bookingForm = BookingForm.builder().build();
        bookingForm.setDepartmentList(departmentService.getAll());
        model.addAttribute("bookingForm", bookingForm);
        return "booking";
    }

    @PostMapping("/booking")
    public String find(@ModelAttribute BookingForm bookingForm, Model model) {
        LocalDate startingRental = LocalDate.parse(bookingForm.getRentalStart());
        LocalDate endingRental = LocalDate.parse(bookingForm.getRentalEnd());
        bookingForm.setDepartmentList(departmentService.getAll());
        List<Car> cars = carBookingDatesService.findCarByFreeData(startingRental, endingRental, bookingForm.getDepartmentStart());
        model.addAttribute("cars", cars);
        return "booking";
    }

    @GetMapping("/booking-summary")
    public String book(@RequestParam("car_id") Long id,
                       @ModelAttribute BookingForm bookingForm,
                       Model model) {
        Booking booking = Booking.builder().build();
        model.addAttribute("car", carService.getCarById(id));
        return "booking-summary";
    }

    @PostMapping("/book")
    public String book(@ModelAttribute Car car, @ModelAttribute BookingForm bookingForm) {
        LocalDate startingRental = LocalDate.parse(bookingForm.getRentalStart());
        LocalDate endingRental = LocalDate.parse(bookingForm.getRentalEnd());
        carService.changeRentalStatus(car.getId(), RentalStatus.BOOKED, startingRental, endingRental);
        return "booking";
    }

        @PostMapping("/confirm-booking")
        private String confirmBooking (Model model){
//        model.addAttribute("car", carService.)
            return "success";
        }
    }

