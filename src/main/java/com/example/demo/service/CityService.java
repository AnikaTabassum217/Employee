package com.example.demo.service;

import com.example.demo.controller.ApiResponse;
import com.example.demo.entity.City;
import com.example.demo.entity.Employee;
import com.example.demo.model.CityDto;
import com.example.demo.model.EmployeeDto;
import com.example.demo.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CityService {
    @Autowired
    CityRepository cityRepository;

//    public CityDto createCity(CityDto cityDto) {
//
//        City city = new City(); // city is a Entity object
//
//        city.setName(cityDto.getName());
//        city.setPostalCode(cityDto.getPostalCode());
//
//        city = cityRepository.save(city);
//        cityDto.setId(city.getId());
//        return cityDto;
//    }

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



    public City findCityById(CityDto cityDto) {
        Optional<City> city = cityRepository.findById(cityDto.getId());
        if(city.isPresent()) {
            return city.get();
        }
        return null;
    }

    public boolean deleteCity(long id) throws Exception {
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
