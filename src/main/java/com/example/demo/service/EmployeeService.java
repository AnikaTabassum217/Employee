package com.example.demo.service;

import com.example.demo.controller.ApiResponse;
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

    public ApiResponse createEmployee(EmployeeDto employeeDto) {
        ApiResponse response = new ApiResponse(false);
        Optional<City> cityOptional = cityRepository.findById(employeeDto.getCityId());
            try{
                Employee employee = new Employee(); // create is a Entity object
            //        create.setId(employeeDto.getId());

//                if(employee.getName().equals(employeeDto.getName()))
//                {
//                    throw new Exception("User name already exist");
//                }
                if (employeeRepository.findByName(employeeDto.getName()).isPresent()) {
                    throw new Exception("User name already exist");
                }
                employee.setName(employeeDto.getName());
                employee.setAge(employeeDto.getAge());
                employee.setAddress(employeeDto.getAddress());
                employee.setPhoneNumber(employeeDto.getPhoneNumber());
                employee.setDateCreate(employeeDto.getDateCreate());
                employee.setDateUpdate(employeeDto.getDateUpdate());
                employee.setDepartment(employeeDto.getDepartment());
                employee.setCity(cityOptional.get());
                employee = employeeRepository.save(employee);
                employeeDto.setId(employee.getId());

            }catch (Exception e )
            {
                throw new RuntimeException(e.getMessage());
            }
        //return employeeDto;
        response.setSuccess(true);
        response.setMessage("Created");
        return response;
        }



    public EmployeeDto updateEmployee(EmployeeDto employeeDto) {
        Optional<Employee> employeeOptional = employeeRepository.findById(employeeDto.getId());
        Optional<City> cityOptional = cityRepository.findById(employeeDto.getCityId());

        if (employeeOptional.isPresent()) {

            Employee employee = new Employee();
            employee.setId(employeeDto.getId());
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

    public boolean deleteEmployee(Long id) throws Exception {
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

    public Employee findEmployeeById(EmployeeDto employeeDto) {
        Optional<Employee> employee = employeeRepository.findById(employeeDto.getId());
        if(employee.isPresent()) {
            return employee.get();
        }
        return null;
    }
    public List<Employee> findEmployeeByCityId(EmployeeDto employeeDto) {
        List<Employee> employee = employeeRepository.findEmployeeByCity(employeeDto.getCityId());
        return employee;
    }

}
