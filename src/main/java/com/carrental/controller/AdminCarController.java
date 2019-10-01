package com.carrental.controller;

import com.carrental.model.Car;
import com.carrental.model.dto.CarDTO;
import com.carrental.model.enums.RentalStatus;
import com.carrental.service.CarService;
import com.carrental.service.DepartmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminCarController {

    private CarService carService;
    private DepartmentService departmentService;

    public AdminCarController(CarService carService, DepartmentService departmentService) {
        this.carService = carService;
        this.departmentService = departmentService;
    }

    @GetMapping("/add-car")
    public String addCar(Model model) {
        CarDTO carDTO = CarDTO.builder().build();
        carDTO.setDepartments(departmentService.getAll());
        model.addAttribute("carDTO", carDTO);
        return "admin/add-car";
    }

    @PostMapping("/add-car")
    public String addCar(@ModelAttribute CarDTO carDTO, Model model) {
        model.addAttribute("carDTO", carDTO);

        Car car = Car.builder()
                .make(carDTO.getMake())
                .model(carDTO.getModel())
                .bodyType(carDTO.getBodyType())
                .seats(carDTO.getSeats())
                .doors(carDTO.getDoors())
                .engine(carDTO.getEngine())
                .aircon(carDTO.getAircon())
                .manualGearbox(carDTO.getManualGearbox())
                .satnav(carDTO.getSatnav())
                .bluetooth(carDTO.getBluetooth())
                .department(carDTO.getDepartment())
                .fee(carDTO.getFee())
                .rentalStatus(RentalStatus.AVAILABLE)
                .build();

        carService.saveCar(car);

        return "redirect:/cars";
    }
}
