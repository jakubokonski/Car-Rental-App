package com.carrental.model.dto;

import com.carrental.model.CarBookingDates;
import com.carrental.model.Department;
import com.carrental.model.enums.BodyType;
import com.carrental.model.enums.Engine;
import com.carrental.model.enums.RentalStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarDTO {

    @NotEmpty(message = "poleWymagane")
    private String make;
    @NotEmpty(message = "poleWymagane")
    private String model;

    @Enumerated(value = EnumType.STRING)
    private BodyType bodyType;

    private String seats;
    private String doors;
    @Enumerated(value = EnumType.STRING)
    private Engine engine;
    private Boolean aircon;
    private Boolean manualGearbox;
    private Boolean satnav;
    private Boolean bluetooth;
    private BigDecimal fee;
    private Department department;
    private List<Department> departments;

}
