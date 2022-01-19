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



    @DeleteMapping("/deleteCity/{id}")
    public ResponseEntity deleteCity(@PathVariable Long id) throws Exception {

        ApiResponse response = new ApiResponse(false);
        try{
            boolean result = cityService.deleteCity(id);
            response.setSuccess(result);
            response.setMessage("Deleted");
        }catch (Exception ex){
            response.setError(ex.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
