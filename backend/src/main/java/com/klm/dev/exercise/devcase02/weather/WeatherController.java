package com.klm.dev.exercise.devcase02.weather;

import com.klm.dev.exercise.devcase02.version.ApiVersion;
import org.modelmapper.ModelMapper;

import com.klm.dev.exercise.devcase02.weather.model.response.Weather;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@Slf4j
@RestController

public class WeatherController {
    @Autowired
    private WeatherClient weatherClient;

    @Autowired
    private ModelMapper modelMapper;

    @CrossOrigin
    @RequestMapping(value = "/weather/{cityName}", produces = "application/json", method = RequestMethod.GET)
    public ResponseEntity<Object> getWeather(@PathVariable String cityName, @RequestHeader(value = "Accept") ApiVersion version) {

        if (cityName.length() != 3) {
            return new ResponseEntity("\"Invalid airport code. Please use a three-letter airport code, such as: AMS (Amsterdam).\"", HttpStatus.NOT_ACCEPTABLE);
        }
        if (version.equals(ApiVersion.V1)) {

            com.klm.dev.exercise.devcase02.weather.model.backend.Weather weather = weatherClient.getWeather(cityName);
            return new ResponseEntity(weather, HttpStatus.OK);
        } else {
            com.klm.dev.exercise.devcase02.weather.model.backend.Weather weather = weatherClient.getWeather(cityName);
            return new ResponseEntity(changeModel(weather), HttpStatus.OK);
        }
    }


    private Weather changeModel(com.klm.dev.exercise.devcase02.weather.model.backend.Weather weather) {
        return modelMapper.map(weather, Weather.class);
    }

}

