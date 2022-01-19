package com.example.demo.controller;

import com.example.demo.entity.City;
import com.example.demo.entity.Employee;
import com.example.demo.model.CityDto;
import com.example.demo.model.EmployeeDto;
import com.example.demo.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CityController {

    @Autowired
    CityService cityService;

//    @PostMapping("/createCity")
//    public ResponseEntity createCity(@RequestBody CityDto cityDto) {
//
//        cityDto = cityService.createCity(cityDto);
//        return ResponseEntity.ok(cityDto);
//    }

    @PostMapping("/createCity")
    public ResponseEntity<?> createCity(@RequestBody CityDto cityDto) throws  Exception{

        ApiResponse response = new ApiResponse(false);
        try{
            response = cityService.createCity(cityDto);
        }catch (Exception ex){
            response.setError(ex.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/updateCity")
    public ResponseEntity updateCity(@RequestBody CityDto cityDto) {
        cityDto = cityService.updateCity(cityDto);
        return ResponseEntity.ok(cityDto);
    }

    @GetMapping(value = "/findAllCity")
    public List<City> findCity() throws Exception {

        return cityService.findCity();
    }

    @GetMapping(value = "/findCityById")
    public Optional<City> findCityById(@RequestParam("id") Long id) {
       Optional <City> city = cityService.findCityById(id);
        return city;
    }

//    @GetMapping(value = "/findEmployeeById")
//    public List <Employee> findEmployeeById(@RequestParam("id") Long id) {
//        List <Employee> employee = employeeService.findEmployeeById(id);
//        return employee;
//    }

    @DeleteMapping("/deleteCity/{id}")
    public ResponseEntity deleteCity(@PathVariable long id) throws Exception {
        boolean result = cityService.deleteCity(id);
        return ResponseEntity.ok(result);
    }
}
