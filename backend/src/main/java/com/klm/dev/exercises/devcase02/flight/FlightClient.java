package com.klm.dev.exercises.devcase02.flight;

import com.klm.dev.exercises.devcase02.weather.Weather;
import com.klm.dev.exercises.devcase02.weather.WeatherClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Configuration
@PropertySource("classpath:Flight.properties")
public class FlightClient {
    private RestTemplate restTemplate = new RestTemplate();
    private static final int NTHREDS = 10;

    @Autowired
    private WeatherClient weatherClient;

    @Value("${flights.url}")
    private String url;

    public Flight[] getFlights() {
        Flight[] flights = restTemplate.getForObject(url, Flight[].class);
        for (Flight flight: flights){
           String[] cityName = flight.getRoute();
            List<Weather> weathers = weatherClient.getWeathers(cityName);
            Weather[] weather = new Weather[weathers.size()];
            weather = weathers.toArray(weather);
            flight.setWeather(weather);

        }
        return flights;
    }
}

