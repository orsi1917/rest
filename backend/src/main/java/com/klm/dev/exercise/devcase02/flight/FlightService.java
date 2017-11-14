package com.klm.dev.exercise.devcase02.flight;

import com.klm.dev.exercise.devcase02.weather.Weather;
import com.klm.dev.exercise.devcase02.weather.WeatherClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Configuration
@PropertySource("classpath:flight.properties")
public class FlightService {

    @Autowired
    FlightClient flightClient;

    @Autowired
    WeatherClient weatherClient;

    public List<Flight> getFlights() {
        List<Flight> flights = flightClient.getFlights();
        return flights;
    }

    public List<Flight> getFlightsWithWeather() {
        List<Flight> flights = flightClient.getFlights();
        List<String> locations = getAllDestinations(flights);
        Map<String, Weather> weathers = weatherClient.getWeathers(locations);
        flights = addWeatherToFlight(flights, weathers);
        return flights;
    }


    private List<String> getAllDestinations(List<Flight> flights) {
        Set<String> locations = new HashSet<>();
        flights.forEach(flight -> {
            flight.getRoute().forEach(location -> {
                locations.add(location);
            });
        });
        List<String> location = new ArrayList<String>(locations);
        return location;

    }

    private List<Flight> addWeatherToFlight(List<Flight> flights, Map<String, Weather> weathers) {
        flights.forEach(flight -> {
            List<Weather> flightWeather = new ArrayList<>();
            flight.getRoute().forEach(location -> {
                Weather weather = weathers.get(location);
                flightWeather.add(weather);
            });
            flight.setWeather(flightWeather);
        });
        return flights;

    }
}




