package com.example.demo.controller;

import com.example.demo.entity.Employee;
import com.example.demo.model.EmployeeDto;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping("/update")
    public ResponseEntity updateEmployee(@RequestBody EmployeeDto employeeDto) {
        employeeDto = employeeService.updateEmployee(employeeDto);
        return ResponseEntity.ok(employeeDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteEmployee(@PathVariable Long id) throws Exception {
        boolean result = employeeService.deleteEmployee(id);
        return ResponseEntity.ok(result);
    }


    @GetMapping(value = "/findAllEmployee")
    public List<Employee> findEmployee() throws Exception {

        return employeeService.findEmployee();
    }

    @GetMapping(value = "/findEmployeeById")
    public List <Employee> findEmployeeById(@RequestParam("id") Long id) {
        List <Employee> employee = employeeService.findEmployeeById(id);
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
    public List<Employee> findEmployeeByEmployeeAddress(@RequestParam("address") String address) {
        List<Employee> employee = employeeService.findEmployeeByEmployeeAddress(address);
        return employee;
    }
}
