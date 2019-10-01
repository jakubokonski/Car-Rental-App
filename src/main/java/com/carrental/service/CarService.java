package com.carrental.service;

import com.carrental.model.Car;
import com.carrental.model.CarBookingDates;
import com.carrental.model.Department;
import com.carrental.model.dto.EditCarForm;
import com.carrental.model.enums.RentalStatus;
import com.carrental.repository.CarBookingDatesRepository;
import com.carrental.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;
    @Autowired
    private CarBookingDatesRepository carBookingDatesRepository;

    public void saveCar(Car car) {
        carRepository.save(car);
    }

    public List<Car> getCars() {
        return carRepository.findAll();
    }

    public Car getCarById(Long id) {
        return carRepository.getOne(id);
    }

    public List<Car> getCarByDepartment(Department department) {
        return carRepository.findByDepartment(department);
    }


    public void changeRentalStatus(Long id, RentalStatus rentalStatus, LocalDate start, LocalDate stop) {
        Car car = carRepository.getOne(id);
        CarBookingDates carBookingDates = CarBookingDates.builder()
                .rentalStatus(rentalStatus)
                .startDate(start)
                .endDate(stop)
                .car(car)
                .build();
        carBookingDatesRepository.save(carBookingDates);

    }

    @Transactional
    public void editCarDetails(EditCarForm editCarForm) {

        Car car = carRepository.getOne(editCarForm.getId());
        car.setId(editCarForm.getId());
        car.setMake(editCarForm.getMake());
        car.setModel(editCarForm.getModel());
        car.setBodyType(editCarForm.getBodyType());
        car.setRentalStatus(editCarForm.getRentalStatus());
        car.setFee(editCarForm.getFee());
        car.setDepartment(editCarForm.getDepartment());
        carRepository.save(car);
    }

}
