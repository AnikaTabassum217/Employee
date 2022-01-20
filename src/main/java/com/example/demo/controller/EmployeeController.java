package com.example.demo.controller;

import com.example.demo.entity.Employee;
import com.example.demo.model.CityDto;
import com.example.demo.model.EmployeeDto;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/create")
    public ResponseEntity<?> createEmployee(@RequestBody EmployeeDto employeeDto) throws  Exception{

        ApiResponse response = new ApiResponse(false);
        try{
            response = employeeService.createEmployee(employeeDto);
        }catch (Exception ex){
            response.setError(ex.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

//    @PutMapping("/update")
//    public ResponseEntity updateEmployee(@RequestBody EmployeeDto employeeDto) {
//        employeeDto = employeeService.updateEmployee(employeeDto);
//        return ResponseEntity.ok(employeeDto);
//    }

//    @PutMapping("/update")
//    public ResponseEntity<?> updateEmployee(@RequestBody EmployeeDto employeeDto) throws  Exception{
//
//        ApiResponse response = new ApiResponse(false);
//        try{
//            response = employeeService.updateEmployee(employeeDto);
//        }catch (Exception ex){
//            response.setError(ex.getMessage());
//        }
//
//        return ResponseEntity.status(HttpStatus.OK).body(response);
//    }

    @PutMapping("/update")
    public ResponseEntity<?> updateEmployee(@RequestBody EmployeeDto employeeDto) throws Exception {

        ApiResponse response = new ApiResponse(false);
        try {
            response = employeeService.updateEmployee(employeeDto);
        } catch (Exception ex) {
            response.setError(ex.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteEmployee(@PathVariable Long id) throws Exception {

        ApiResponse response = new ApiResponse(false);
        try{
            boolean result = employeeService.deleteEmployee(id);
            response.setSuccess(result);
            response.setMessage("Deleted");
        }catch (Exception ex){
            response.setError(ex.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @GetMapping(value = "/findAllEmployee")
    public List<Employee> findEmployee() throws Exception {

        return employeeService.findEmployee();
    }

//    @GetMapping(value = "/findEmployeeById")
//    public Optional<Employee> findEmployeeById(@RequestParam("id") Long id) {
//        Optional <Employee> employee = employeeService.findEmployeeById(id);
//        return employee;
//    }

   @GetMapping(value = "/findEmployeeById/{id}")

    public  Employee findEmployeeById(@PathVariable Long id) {
        Employee employee = employeeService.findEmployeeById(id);
        return employee;
    }

    @GetMapping(value = "/findEmployeeByCityId")
    public List<Employee> findEmployeeByCityId(@RequestParam("cityId") Long cityId) {
        List<Employee> employee = employeeService.findEmployeeByCityId(cityId);
        return employee;
    }

    @GetMapping(value = "/findEmployeeByEmployeeName")
    public List<Employee> findEmployeeByEmployeeName(@RequestParam ("name") String name) {
        List<Employee> employee = employeeService.findEmployeeByEmployeeName(name);
        return employee;
    }

    @GetMapping(value = "/findEmployeeByEmployeeAge")
    public List<Employee> findEmployeeByEmployeeAge(@RequestParam ("age") int age) {
        List<Employee> employee = employeeService.findEmployeeByEmployeeAge(age);
        return employee;
    }

    @GetMapping(value = "/findEmployeeByEmployeeDept")
    public List<Employee> findEmployeeByEmployeeDept(@RequestParam ("department")String department) {
        List<Employee> employee = employeeService.findEmployeeByEmployeeDept(department);
        return employee;
    }


    @GetMapping(value = "/findEmployeeByEmployeeAddress")
    public List<Employee> findEmployeeByEmployeeAddress(@RequestParam ("address")String address) {
        List<Employee> employee = employeeService.findEmployeeByEmployeeAddress(address);
        return employee;
    }
}
