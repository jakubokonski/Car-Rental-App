package com.carrental.service;

import com.carrental.repository.CarBookingDatesRepository;
import com.carrental.model.Car;
import com.carrental.model.CarBookingDates;
import com.carrental.model.Department;
import com.carrental.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CarBookingDatesService {

    @Autowired
    private CarBookingDatesRepository carBookingDatesRepository;
    @Autowired
    private CarRepository carRepository;

    public List<CarBookingDates> getAll() {
        return carBookingDatesRepository.findAll();
    }

    public List<Car> findCarByFreeData(LocalDate startData, LocalDate endData, Department department) {

        if (!(startData.isBefore(endData))) {
            return new ArrayList<>();
        }

        List<CarBookingDates> carBookingDatesList = new ArrayList<>();
        Set<Car> cars = new HashSet<>();
        List<Car> carListByDepartment = new ArrayList<>();

        carListByDepartment = carRepository.findByDepartment(department);
        if (carListByDepartment == null) {
            return carListByDepartment = new ArrayList<>();
        }

        for (Car car : carListByDepartment) {
            if (car.getCarBookingDates().isEmpty()) {
                cars.add(car);
            }
            for (CarBookingDates date : car.getCarBookingDates()) {
                if (startData.isAfter(date.getEndDate())) {
                    carBookingDatesList.add(date);
                }
            }
        }

            for (CarBookingDates data : carBookingDatesList) {
                if ((startData.isAfter(data.getEndDate()) && endData.isAfter(data.getEndDate()))
                        || (startData.isBefore(data.getStartDate()) && endData.isBefore(data.getStartDate()))) {
                    if (data.getCar().getDepartment() == department) {
                        cars.add(data.getCar());
                    }
                } else {
                    if (data.getCar().getDepartment() == department) {
                        cars.remove(data.getCar());
                    }
                }
            }
            List<Car> carList = new ArrayList<>(cars);
            return carList;
        }

}
