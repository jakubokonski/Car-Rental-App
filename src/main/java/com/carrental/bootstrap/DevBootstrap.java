package com.carrental.bootstrap;

import com.carrental.model.*;
import com.carrental.model.enums.BodyType;
import com.carrental.model.enums.Colour;
import com.carrental.model.enums.RentalStatus;
import com.carrental.model.enums.Role;
import com.carrental.repository.CarBookingDatesRepository;
import com.carrental.repository.CarRepository;
import com.carrental.repository.ClientRepository;
import com.carrental.repository.EmployeeRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private CarRepository carRepository;
    private EmployeeRepository employeeRepository;
    private ClientRepository clientRepository;
    private CarBookingDatesRepository carBookingDatesRepository;

    public DevBootstrap(CarRepository carRepository, EmployeeRepository employeeRepository, ClientRepository clientRepository, CarBookingDatesRepository carBookingDatesRepository) {
        this.carRepository = carRepository;
        this.employeeRepository = employeeRepository;
        this.clientRepository = clientRepository;
        this.carBookingDatesRepository = carBookingDatesRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initCar();
        initEmployee();
        initClient();
        initBooking();
    }

    private void initCar() {

        Car mazda = Car.builder()
                .make("Mazda")
                .model("3")
                .bodyType(BodyType.MEDIUM)
                .year("2017")
                .colour(Colour.SILVER)
                .mileage(BigDecimal.valueOf(2300))
                .rentalStatus(RentalStatus.AVAILABLE)
                .fee(BigDecimal.valueOf(180))
                .department(Department.builder()
                        .name("Karowa")
                        .address(Address.builder()
                                .street("Karowa")
                                .buildingNumber("6")
                                .city("Warszawa")
                                .build())
                        .build())
                .build();
        carRepository.save(mazda);

        Car toyota = Car.builder()
                .make("Toyota")
                .model("Prius")
                .bodyType(BodyType.LARGE)
                .year("2018")
                .colour(Colour.BLACK)
                .mileage(BigDecimal.valueOf(1200))
                .rentalStatus(RentalStatus.AVAILABLE)
                .fee(BigDecimal.valueOf(200))
                .department(Department.builder()
                        .name("WRC")
                        .address(Address.builder()
                                .street("Wrocławska")
                                .buildingNumber("33")
                                .city("Warszawa")
                                .build())
                        .build())
                .build();
        carRepository.save(toyota);


        Car ford = Car.builder()
                .make("Ford")
                .model("Transit")
                .bodyType(BodyType.LARGE)
                .year("2014")
                .colour(Colour.BLACK)
                .mileage(BigDecimal.valueOf(120000))
                .rentalStatus(RentalStatus.BOOKED)
                .fee(BigDecimal.valueOf(200))
                .department(Department.builder()
                        .name("Get&Go")
                        .address(Address.builder()
                                .street("Kolejowa")
                                .buildingNumber("6")
                                .city("Gdansk")
                                .build())
                        .build())
                .build();

        List<CarBookingDates> carBookingDatesList = new ArrayList<>();
        CarBookingDates date1 = CarBookingDates.builder()
                .startDate(LocalDate.of(2019, 10, 16))
                .endDate(LocalDate.of(2019,10,20))
                .rentalStatus(RentalStatus.BOOKED)
                .car(ford)
                .build();
        carBookingDatesList.add(date1);
        carBookingDatesRepository.save(date1);
        carRepository.save(ford);
    }

    private void initEmployee() {
        employeeRepository.save(Employee.builder()
                .name("Jim")
                .surname("Beam")
                .role(Role.ADMIN)
                .build());
    }

    private void initClient() {

        Client client = Client.builder()
                .name("Tomasz")
                .surname("Kowalski")
                .email("tk@vp.pl")
                .address(Address.builder()
                        .city("Szczecin")
                        .street("Zamenhoffa")
                        .buildingNumber("10")
                        .build())
                .password("qwe")
                .build();
        PasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        client.setPassword(bCryptPasswordEncoder.encode(client.getPassword()));
        clientRepository.save(client);

    }

    private void initBooking() {

    }
}
