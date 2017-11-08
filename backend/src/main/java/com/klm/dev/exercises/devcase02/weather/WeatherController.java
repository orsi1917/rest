package com.klm.dev.exercises.devcase02.weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class WeatherController {
    @Autowired
    private WeatherClient weatherClient;

    @CrossOrigin
    @GetMapping("/weather/{cityName}")
    public Weather getWeather(@PathVariable String cityName) {
        return weatherClient.getWeather(cityName);
    }

    @CrossOrigin
    @GetMapping("/weathers/" )
    public List<Weather> getWeather (String[] cityName) {
       return weatherClient.getWeathers(cityName);

        }

}

