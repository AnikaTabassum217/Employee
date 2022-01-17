package com.example.demo.service;

import com.example.demo.entity.City;
import com.example.demo.entity.Employee;
import com.example.demo.model.EmployeeDto;
import com.example.demo.repository.CityRepository;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    CityRepository cityRepository;

    public EmployeeDto createEmployee(EmployeeDto employeeDto) {

        Optional<City> cityOptional = cityRepository.findById(employeeDto.getCityId());

            Employee employee = new Employee(); // create is a Entity object
//        create.setId(employeeDto.getId());
            employee.setName(employeeDto.getName());
            employee.setAge(employeeDto.getAge());
            employee.setAddress(employeeDto.getAddress());
            employee.setPhoneNumber(employeeDto.getPhoneNumber());
            employee.setDateCreate(employeeDto.getDateCreate());
            employee.setDateUpdate(employeeDto.getDateUpdate());
            employee.setDepartment(employeeDto.getDepartment());
            employee.setCity(cityOptional.get());
            employeeRepository.save(employee);

            return employeeDto;
        }



    public EmployeeDto updateEmployee(EmployeeDto employeeDto, long id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        Optional<City> cityOptional = cityRepository.findById(employeeDto.getCityId());

        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            employee.setAddress(employeeDto.getAddress());
            employee.setPhoneNumber(employeeDto.getPhoneNumber());
            //employee.setDateUpdate(employee.getDateUpdate());
            employee.setName(employeeDto.getName());
            employee.setDepartment(employeeDto.getDepartment());
            employee.setAge(employeeDto.getAge());
            employee.setCity(cityOptional.get());

            employee = employeeRepository.save(employee);
            employeeDto.setId(employee.getId());

            //return employeeDto;
        }
        return employeeDto;
    }

    public boolean deleteEmployee(EmployeeDto employeeDto, long id) throws Exception {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);

        if (employeeOptional.isPresent()) {

            Employee employee = employeeOptional.get();
            employeeRepository.delete(employee);

        } else {
            throw new Exception("User not found");
        }

        return true;
    }

    public List<Employee> findEmployee() {
        return employeeRepository.findAll();
    }

//    @Transactional
//    public Employee findEmployeeById(Long id) {
//        return employeeRepository.findById(id).get();
//    }

    public Employee findEmployeeById(long id) {
        Employee employee = employeeRepository.findById(id).get();
        employee.getCity().getName();
        return employee;
    }

}
