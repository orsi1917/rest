package com.klm.dev.exercise.devcase02.weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

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
    public Map<String, Weather> getWeather (List<String> cityName) {
       return weatherClient.getWeathers(cityName);

        }

}

