package com.klm.dev.exercises.devcase02.flight;

import com.klm.dev.exercises.devcase02.weather.Weather;
import com.klm.dev.exercises.devcase02.weather.WeatherClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@Configuration
@PropertySource("classpath:flight.properties")
public class FlightClient {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WeatherClient weatherClient;
    @Autowired
    private FlightService flightService;

    @Value("${flights.url}")
    private String url;

    public List<Flight> getFlights() {
        List <Flight> flights = Arrays.asList(restTemplate.getForObject(url, Flight[].class));
        Set<String> locations = new HashSet<>();
        return flights;
    }
    public List<Flight> getFlightsWithWeather() {
        List <Flight> flights = Arrays.asList(restTemplate.getForObject(url, Flight[].class));
        List <String>locations = flightService.getAllDestinations(flights);
        Map<String, Weather> weathers = weatherClient.getWeathers(locations);
        flights = flightService.addWeatherToFlight(flights, weathers );
        return flights;
    }
}
