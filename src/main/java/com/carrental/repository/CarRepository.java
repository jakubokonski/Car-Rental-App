package com.carrental.repository;

import com.carrental.model.Car;
import com.carrental.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> findByDepartment(Department department);
}
