package com.example.demo.controller;

import com.example.demo.entity.City;
import com.example.demo.model.CityDto;
import com.example.demo.model.EmployeeDto;
import com.example.demo.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @RequestMapping(value = "/findAllCity", method = RequestMethod.GET)
    public List<City> findCity() throws Exception {

        return cityService.findCity();
    }

    @RequestMapping(value = "/findCityById", method = RequestMethod.GET)
    public @ResponseBody
    City findCityById(@RequestBody CityDto cityDto) {
        City city = cityService.findCityById(cityDto);
        return city;
    }

    @DeleteMapping("/deleteCity/{id}")
    public ResponseEntity deleteCity(@PathVariable long id) throws Exception {
        boolean result = cityService.deleteCity(id);
        return ResponseEntity.ok(result);
    }
}
