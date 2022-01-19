package com.example.demo.service;

import com.example.demo.controller.ApiResponse;
import com.example.demo.entity.City;
import com.example.demo.entity.Employee;
import com.example.demo.model.CityDto;
import com.example.demo.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {
    @Autowired
    CityRepository cityRepository;



    public ApiResponse createCity(CityDto cityDto) {
        ApiResponse response = new ApiResponse(false);

        //City city = new City(); // city is a Entity object

        try{
            City city = new City(); // city is a Entity object
            if (cityRepository.findByName(cityDto.getName()).isPresent()) {
                throw new Exception("City name already exist");
            }
        city.setName(cityDto.getName());
        city.setPostalCode(cityDto.getPostalCode());

        city = cityRepository.save(city);
        cityDto.setId(city.getId());
        //return cityDto;
    }
        catch (Exception e )
        {
            throw new RuntimeException(e.getMessage());
        }
        //return employeeDto;
        response.setSuccess(true);
        response.setMessage("Created");
        return response;
    }

    public CityDto updateCity(CityDto cityDto) {
        Optional<City> cityOptional = cityRepository.findById(cityDto.getId());

        if (cityOptional.isPresent()) {


            City city = cityOptional.get();

            city.setName(cityDto.getName());
            city.setPostalCode(cityDto.getPostalCode());

            city = cityRepository.save(city);
            cityDto.setId(city.getId());

            //return employeeDto;
        }
        return cityDto;
    }

    public List<City> findCity() {
        return cityRepository.findAll();
    }




    public Optional<City> findCityById(Long id) {
        Optional<City> city = cityRepository.findById(id);
        return city;
    }




    public boolean deleteCity(Long id) throws Exception {
        Optional<City> cityOptional = cityRepository.findById(id);

        if (cityOptional.isPresent()) {

            City city = cityOptional.get();
            cityRepository.delete(city);

        } else {
            throw new Exception("User not found");
        }
        return true;
    }

}
