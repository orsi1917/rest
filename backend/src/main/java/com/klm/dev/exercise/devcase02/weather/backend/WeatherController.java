package com.klm.dev.exercise.devcase02.weather.backend;

import com.klm.dev.exercise.devcase02.versioncontrol.Versions;
import org.modelmapper.ModelMapper;

import com.klm.dev.exercise.devcase02.weather.response.Weather;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController

public class WeatherController {
    @Autowired
    private WeatherClient weatherClient;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private HttpServletRequest request;

    @CrossOrigin
    @RequestMapping(value = "/weather/{cityName}", produces = "application/json", method = RequestMethod.GET)
    public ResponseEntity<Weather> getWeather_v1(@PathVariable String cityName, @RequestHeader(value = "Accept", defaultValue = "2.0") Versions version) {

        if (cityName.length() != 3) {

            return new ResponseEntity("\"Invalid airport code. Please use a three-letter airport code, such as: AMS (Amsterdam).\"", HttpStatus.NOT_ACCEPTABLE);
        }
        if (version.equals(Versions.V1)) {

            com.klm.dev.exercise.devcase02.weather.backend.Weather weather = weatherClient.getWeather(cityName);
            return new ResponseEntity(weather, HttpStatus.OK);
        } else {
            com.klm.dev.exercise.devcase02.weather.backend.Weather weather = weatherClient.getWeather(cityName);
            return new ResponseEntity(changeModel(weather), HttpStatus.OK);
        }
    }


    @CrossOrigin
    @GetMapping("/weathers/")
    public Map<String, com.klm.dev.exercise.devcase02.weather.backend.Weather> getWeather(List<String> cityName) {
        return weatherClient.getWeathers(cityName);
    }

    private com.klm.dev.exercise.devcase02.weather.response.Weather changeModel(com.klm.dev.exercise.devcase02.weather.backend.Weather weather) {
        return modelMapper.map(weather, com.klm.dev.exercise.devcase02.weather.response.Weather.class);

    }

}

