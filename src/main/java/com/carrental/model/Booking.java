package com.carrental.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate bookingDate;
    @OneToOne
    private Client client;
    @OneToOne
    private Car car;
    private LocalDate rentalStart;
    private LocalDate rentalEnd;
    @OneToOne
    private Department departmentStart;
    @OneToOne
    private Department departmentEnd;
    private BigDecimal price;

}
