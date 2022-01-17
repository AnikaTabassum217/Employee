package com.example.demo.service;

import com.example.demo.entity.City;
import com.example.demo.model.CityDto;
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

    public CityDto createCity(CityDto cityDto) {

        City city = new City(); // city is a Entity object

        city.setName(cityDto.getName());
        city.setPostalCode(cityDto.getPostalCode());

        city = cityRepository.save(city);
        cityDto.setId(city.getId());
        return cityDto;
    }

    public CityDto updateCity(CityDto cityDto, long id) {
        Optional<City> cityOptional = cityRepository.findById(id);

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

    @Transactional
    public City findCityById(Long id) {
        return cityRepository.findById(id).get();
    }

    public boolean deleteCity(CityDto cityDto, long id) throws Exception {
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