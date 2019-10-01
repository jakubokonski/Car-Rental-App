package com.carrental.model.dto;

import com.carrental.model.Car;
import com.carrental.model.Client;
import com.carrental.model.Department;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingForm {

    private LocalTime bookingDate;
    private Client client;
    private Car car;
    private String rentalStart;
    private String rentalEnd;
    private Department departmentStart;
    private Department departmentEnd;
    private BigDecimal price;
    private List<Department> departmentList;
    private List<Car> cars;


}
