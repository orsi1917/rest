package com.klm.dev.exercise.devcase02.flight;

import com.klm.dev.exercise.devcase02.weather.Weather;
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
        Set<String> locations = flights.stream()
                .map(flight -> flight.getRoute())
                .flatMap(location -> location.stream())
                .collect(Collectors.toSet());
        return new ArrayList<String>(locations);

    }

    private List<Flight> addWeatherToFlight(List<Flight> flights, Map<String, Weather> weathers) {
        flights.forEach(flight -> {
            List<Weather> flightWeather =
                    flight.getRoute().stream()
                            .map(location -> {
                                Weather weather = weathers.get(location);
                                return weather;
                            })
                            .collect(Collectors.toList());
            flight.setWeather(flightWeather);
        });
        return flights;

    }
}




