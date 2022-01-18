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

//        employeeDto = employeeService.createEmployee(employeeDto);
//        return ResponseEntity.ok(employeeDto);
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


    @RequestMapping(value = "/findAllEmployee", method = RequestMethod.GET)
    public List<Employee> findEmployee() throws Exception {

        return employeeService.findEmployee();
    }

    @RequestMapping(value = "/findEmployeeById", method = RequestMethod.POST)
    public @ResponseBody
    Employee findEmployeeById(@RequestBody EmployeeDto employeeDto) {
        Employee employee = employeeService.findEmployeeById(employeeDto);
        return employee;
    }

    @RequestMapping(value = "/findEmployeeByCityId", method = RequestMethod.POST)
    public @ResponseBody
    List<Employee> findEmployeeByCityId(@RequestBody EmployeeDto employeeDto) {
        List<Employee> employee = employeeService.findEmployeeByCityId(employeeDto);
        return employee;
    }
}
