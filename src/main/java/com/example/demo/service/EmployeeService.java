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
<<<<<<< HEAD
=======

>>>>>>> b9a1de26350cf42e8ccdb0ba6988ff533c2b0301
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
//
//    public EmployeeDto updateEmployee(EmployeeDto employeeDto) {
//        Optional<Employee> employeeOptional = employeeRepository.findById(employeeDto.getId());
//        Optional<City> cityOptional = cityRepository.findById(employeeDto.getCityId());
//
//        if (employeeOptional.isPresent()) {
//
//            Employee employee = new Employee();
//            employee.setId(employeeDto.getId());
//            employee.setAddress(employeeDto.getAddress());
//            employee.setPhoneNumber(employeeDto.getPhoneNumber());
//            //employee.setDateUpdate(employee.getDateUpdate());
//            employee.setName(employeeDto.getName());
//            employee.setDepartment(employeeDto.getDepartment());
//            employee.setAge(employeeDto.getAge());
//            employee.setCity(cityOptional.get());
//
//            employee = employeeRepository.save(employee);
//            employeeDto.setId(employee.getId());
//
//
//            //return employeeDto;
//        }
//        return employeeDto;
//    }

    public ApiResponse updateEmployee(EmployeeDto employeeDto) {

        ApiResponse response = new ApiResponse(false);

        Optional<Employee> employeeOptional = employeeRepository.findById(employeeDto.getId());
        Optional<Employee> employeeOPTName = employeeRepository.findByName(employeeDto.getName());
        Optional<City> cityOptional = cityRepository.findById(employeeDto.getCityId());

        try{
            Employee employee = new Employee(); // create is a Entity object
            if (employeeOPTName.isPresent() && employeeDto.getId() !=employeeOPTName.get().getId()) {
                throw new Exception("User name already exist");
            }
            employee.setName(employeeDto.getName());
            employee.setAge(employeeDto.getAge());
            employee.setAddress(employeeDto.getAddress());
            employee.setPhoneNumber(employeeDto.getPhoneNumber());
            //employee.setDateCreate(employeeDto.getDateCreate());
            //employee.setDateUpdate(employeeDto.getDateUpdate());
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

//    public Employee findEmployeeById(Long id) {
//        Optional<Employee> employee = employeeRepository.findById(id);
//        if(employee.isPresent()) {
//            return employee.get();
//        }
//        return null;
//    }

    public Optional<Employee> findEmployeeById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        return employee;
    }
    public List<Employee> findEmployeeByCityId(Long cityId) {
        List<Employee> employee = employeeRepository.findEmployeeByCity(cityId);
        return employee;
    }
    public List<Employee>findEmployeeByEmployeeName (String name) {
        List<Employee> employee = employeeRepository.findEmployeeByEmployeeName(name);
        return employee;
    }
    public List<Employee>findEmployeeByEmployeeAge (int age) {
        List<Employee> employee = employeeRepository.findEmployeeByEmployeeAge(age);
        return employee;
    }

    public List<Employee>findEmployeeByEmployeeDept(String department) {
        List<Employee> employee = employeeRepository.findEmployeeByEmployeeDept(department);
        return employee;
    }

    public List<Employee>findEmployeeByEmployeeAddress(String address) {
        List<Employee> employee = employeeRepository.findEmployeeByEmployeeAddress(address);
        return employee;
    }
<<<<<<< HEAD
=======

    public List<Employee>findEmployeeByEmployeeAddress(EmployeeDto employeeDto) {
        List<Employee> employee = employeeRepository.findEmployeeByEmployeeAddress(employeeDto.getAddress());
        return employee;
    }
>>>>>>> b9a1de26350cf42e8ccdb0ba6988ff533c2b0301
}
