package com.carrental.repository;

import com.carrental.model.CarBookingDates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarBookingDatesRepository extends JpaRepository<CarBookingDates, Long> {
}
