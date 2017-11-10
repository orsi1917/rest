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
@PropertySource("classpath:Flight.properties")
public class FlightClient {
    @Autowired
    private RestTemplate restTemplate;

    private static final int NTHREDS = 20;

    @Autowired
    private WeatherClient weatherClient;

    @Value("${flights.url}")
    private String url;

    public List<Flight> getFlights() {
        //First the method gets all flights. Than it iterates through the flights, and the
        // routs of flights. It adds the airport code-name of the route to a String ArrayList
        // if it is not yet in the list.
        //It converts the ArrayList to an array, and makes a paralell call to the weather API
        // with all locations at once.

        List <Flight> flights = Arrays.asList(restTemplate.getForObject(url, Flight[].class));
        Set<String> locations = new HashSet<>();
        for (Flight flight: flights){
            List<String> cityName = flight.getRoute();
            for (String city : flight.getRoute()){
                    locations.add(city);
            }
        }
        List<String> location = new ArrayList<String>(locations);
         List<Weather> weathers = weatherClient.getWeathers(location);
        //It goes through all flights again, one by one, and checks each step of the route.
        // Then it iterates through the ArrayList of the weathers. If the airport code-name of
        // the flight route is the same as the airport code-name in the location of the weather in
        // the arraylist, it adds the weather to the weather array of the flight.
        for (Flight flight: flights){
            List <Weather> flightWeather = new ArrayList<>();

            for (int i =0; i<flight.getRoute().size(); i++ ) {
                for (Weather weather : weathers){
                    if (weather.getLocation().getLocationCode().equals(flight.getRoute().get(i))){
                        flightWeather.add(weather);
                        continue;
                    }
                }
            }
            flight.setWeather(flightWeather);
        }
        return flights;
    }
}

