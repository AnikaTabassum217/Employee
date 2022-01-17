package com.example.demo.controller;

import com.example.demo.entity.City;
import com.example.demo.model.CityDto;
import com.example.demo.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CityController {

    @Autowired
    CityService cityService;

    @PostMapping("/createCity")
    public ResponseEntity createCity(@RequestBody CityDto cityDto) {

        cityDto = cityService.createCity(cityDto);
        return ResponseEntity.ok(cityDto);
    }

    @PutMapping("/updateCity/{id}")
    public ResponseEntity updateCity(@RequestBody CityDto cityDto,
                                         @PathVariable long id) {
        cityDto = cityService.updateCity(cityDto, id);
        return ResponseEntity.ok(cityDto);
    }

    @RequestMapping(value = "/findAllCity", method = RequestMethod.GET)
    public List<City> findCity() throws Exception {

        return cityService.findCity();
    }

    @RequestMapping(value = "/findCityById/{id}", method = RequestMethod.GET)
    public @ResponseBody
    City findCityById(@PathVariable Long id) {
        City city = cityService.findCityById(id);
        return city;
    }

    @DeleteMapping("/deleteCity/{id}")
    public ResponseEntity deleteCity(@RequestBody CityDto cityDto,
                                         @PathVariable long id) throws Exception {
        boolean result = cityService.deleteCity(cityDto, id);
        return ResponseEntity.ok(result);
    }
}
