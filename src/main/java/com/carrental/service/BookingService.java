package com.carrental.service;

import com.carrental.model.Booking;
import com.carrental.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    @Autowired
    BookingRepository bookingRepository;

    public void addBooking(Booking booking) {
         bookingRepository.save(booking);
    }

    public void bookCar(Long id) {
        System.out.println("Car booked");
    }
}
