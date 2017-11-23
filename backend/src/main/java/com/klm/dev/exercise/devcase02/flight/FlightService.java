package com.klm.dev.exercise.devcase02.flight;

import com.klm.dev.exercise.devcase02.flight.model.backend.Flight;
import com.klm.dev.exercise.devcase02.weather.model.backend.Weather;
import com.klm.dev.exercise.devcase02.weather.WeatherClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Configuration
@PropertySource("classpath:flight.properties")
public class FlightService {

    @Autowired
    FlightClient flightClient;

    @Autowired
    WeatherClient weatherClient;

    public List<Flight> getFlightsWithWeather() {
        List<Flight> flights = flightClient.getFlights();
        List<String> locations = getAllDestinations(flights);
        Map<String, Weather> weathers = weatherClient.getWeathers(locations);
        flights = addWeatherToFlight(flights, weathers);
        return flights;
    }

    private List<String> getAllDestinations(List<Flight> flights) {
        return new ArrayList<String>(
                flights.stream()
                        .map(flight -> flight.getRoute())
                        .flatMap(location -> location.stream())
                        .collect(Collectors.toSet())
        );
    }

    private List<Flight> addWeatherToFlight(List<Flight> flights, Map<String, Weather> weathers) {
        flights.forEach(flight -> flight.setWeather(
                flight.getRoute().stream()
                        .map(location -> weathers.get(location))
                        .collect(Collectors.toList())
                )
        );
        return flights;
    }
}
