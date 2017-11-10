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
public class FlightService {


    public List<String> getAllDestinations(List <Flight> flights) {

        Set<String> locations = new HashSet<>();
        for (Flight flight: flights){
            List<String> cityName = flight.getRoute();
            for (String city : flight.getRoute()){
                    locations.add(city);
            }
        }
        List<String> location = new ArrayList<String>(locations);
        return location;

    }
    public List<Flight> addWeatherToFlight(List <Flight> flights, Map<String, Weather> weathers ) {
        for (Flight flight: flights){
            List <Weather> flightWeather = new ArrayList<>();
            for (String location : flight.getRoute()){
                Weather weather = weathers.get(location);
                flightWeather.add(weather);
            }
            flight.setWeather(flightWeather);
        }
        return flights;

    }
}

