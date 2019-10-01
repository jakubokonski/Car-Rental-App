package com.carrental.controller;

import com.carrental.model.Car;
import com.carrental.model.Department;
import com.carrental.model.dto.BookingForm;
import com.carrental.model.dto.EditCarForm;
import com.carrental.model.enums.RentalStatus;
import com.carrental.service.CarBookingDatesService;
import com.carrental.service.CarService;
import com.carrental.service.DepartmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class CarController {

    private CarService carService;
    private DepartmentService departmentService;
    private CarBookingDatesService carBookingDatesService;

    public CarController(CarService carService, DepartmentService departmentService, CarBookingDatesService carBookingDatesService) {
        this.carService = carService;
        this.departmentService = departmentService;
        this.carBookingDatesService = carBookingDatesService;
    }

    @GetMapping("/car-list")
    public String carList(@ModelAttribute BookingForm bookingForm, Model model) {
        LocalDate startingRental = LocalDate.parse(bookingForm.getRentalStart());
        LocalDate endingRental = LocalDate.parse(bookingForm.getRentalEnd());
        List<Car> cars = carBookingDatesService.findCarByFreeData(startingRental, endingRental, bookingForm.getDepartmentStart());
        model.addAttribute("cars", cars);
        return "/car-list";
    }

//    @GetMapping("/admin/add-car")
//    public String addCar(Model model) {
//        model.addAttribute("car", Car.builder().build());
//        return "admin/add-car";
//    }

//    @PostMapping("/admin/add-car")
//    public String addCar(Model model, Car car) {
//        model.addAttribute("car", car);
//        car.setRentalStatus(RentalStatus.AVAILABLE);
//        carService.saveCar(car);
//        return "success";
//    }

    @GetMapping("/list-cars")
    public String listCars(Model model) {
        model.addAttribute("cars", carService.getCars());
        return "list-cars";
    }

    @GetMapping("/edit-car-details")
    public String editCarDetails(@RequestParam ("id") Long id, Model model) {
        Car car = carService.getCarById(id);
        List<Department> departments = departmentService.getAll();
        model.addAttribute("car", car);
        model.addAttribute("departments", departments);
        EditCarForm editCarForm = EditCarForm.builder()
                .id(car.getId())
                .make(car.getMake())
                .model(car.getModel())
                .bodyType(car.getBodyType())
                .rentalStatus(car.getRentalStatus())
                .fee(car.getFee())
                .department(car.getDepartment())
                .build();
        model.addAttribute("editCarForm", editCarForm);
        return "edit-car-details";
    }

    @PostMapping("/edit-car-details")
    public String editCarDetailsSubmit(@ModelAttribute EditCarForm editCarForm) {
        carService.editCarDetails(editCarForm);
        return "redirect:/list-cars";
    }

}
