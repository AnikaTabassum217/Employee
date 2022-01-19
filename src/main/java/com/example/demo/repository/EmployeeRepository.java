package com.example.demo.repository;

import com.example.demo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;
import java.util.Optional;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByName(String name);
//    public Employee findByName(String name);
//
//    @Query(value = "select * from Employee", nativeQuery = false)
//    public List<Employee> findAllByAnything(String name);
//
//    @Query(value = "select id, name, address, phone_number from employee where city_id = 1", nativeQuery = true)
//    public List<Map<String, Object>> findAllByAnythingInNative(String name);

    @Query(value = "SELECT * from employee where id = ?1", nativeQuery = true)
    List<Employee> findEmployeeById(long id);

    @Query(value = "SELECT * from employee where city_id = ?1", nativeQuery = true)
    List<Employee> findEmployeeByCity(long cityId);

    @Query(value = "SELECT * from employee where name = ?1", nativeQuery = true)
    List<Employee> findEmployeeByEmployeeName(String name);

    @Query(value = "SELECT * from employee where age = ?1", nativeQuery = true)
    List<Employee> findEmployeeByEmployeeAge(int age);

    @Query(value = "SELECT * from employee where department = ?1", nativeQuery = true)
    List<Employee> findEmployeeByEmployeeDept(String dept);

    @Query(value = "SELECT * from employee where address = ?1", nativeQuery = true)
    List<Employee> findEmployeeByEmployeeAddress(String address);

}
