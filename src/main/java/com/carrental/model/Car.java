package com.carrental.model;

import com.carrental.model.enums.BodyType;
import com.carrental.model.enums.Colour;
import com.carrental.model.enums.Engine;
import com.carrental.model.enums.RentalStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //TODO dodać zdjęcie
    private String make;
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

    @Enumerated(value = EnumType.STRING)
    private RentalStatus rentalStatus;
    private BigDecimal fee;

    @ManyToOne(cascade = CascadeType.ALL)
    private Department department;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "car")
    private List<CarBookingDates> carBookingDates;
}
