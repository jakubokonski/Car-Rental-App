package com.carrental.model.dto;

import com.carrental.model.Department;
import com.carrental.model.enums.BodyType;
import com.carrental.model.enums.Colour;
import com.carrental.model.enums.RentalStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EditCarForm {

    private Long id;
    private String make;
    private String model;

    @Enumerated(value = EnumType.STRING)
    private BodyType bodyType;

    //pattern
    private String year;

    @Enumerated(value = EnumType.STRING)
    private Colour colour;
    private BigDecimal mileage;

    @Enumerated(value = EnumType.STRING)
    private RentalStatus rentalStatus;

    private BigDecimal fee;

    @OneToOne(cascade = CascadeType.ALL)
    private Department department;
}
