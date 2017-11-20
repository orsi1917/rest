package com.klm.dev.exercise.devcase02.weather;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;


import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController

public class WeatherController {
    @Autowired
    private WeatherClient weatherClient;

    @Produces(MediaType.APPLICATION_JSON)
    @CrossOrigin
    @RequestMapping( value ="/weather/{cityName}",  headers = "Accept=application/json", produces = "application/json; charset=UTF-8" , method = RequestMethod.GET )
     public ResponseEntity<Weather> getWeather_v1(@PathVariable String cityName, @RequestHeader(value="version", defaultValue="1.0") double version) {
       log.info("version= " + version);
        if (cityName.length()!=3){
            return new ResponseEntity("Invalid airport code. Please use a three-letter airport code, such as: AMS (Amsterdam).", HttpStatus.NOT_ACCEPTABLE);
        }
    Weather weather = weatherClient.getWeather(cityName);
        return new ResponseEntity(weather,HttpStatus.OK );
    }


    @CrossOrigin
    @GetMapping("/weathers/")
    public Map<String, Weather> getWeather(List<String> cityName) {
        return weatherClient.getWeathers(cityName);
    }

}

