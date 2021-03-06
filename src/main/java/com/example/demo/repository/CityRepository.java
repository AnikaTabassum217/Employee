package com.example.demo.repository;

import com.example.demo.entity.City;
import com.example.demo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Long> {

    Optional<City> findByName(String name);
    //Optional<City> findEmployeeByCity(Long id);

}
