package com.example.demo.repository;

import com.example.demo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    //Public Employee findByName(String name);
//
//@Query (value = "select * from Employee", nativeQuery = false)
//
//@Query (value = "select id, name, address, phone_number from employee where city_id = 1", nativeQuery = true)
//
//Public List <Map<String, object>> findAllByAnythingInNative(String name)

    @Query(value = "SELECT * from employee where city_id = ?1", nativeQuery = true)
    List<Employee> findEmployeeByCity(long cityId);
}




