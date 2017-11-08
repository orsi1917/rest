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
//Goes through all flights one by one, and calls the weather information on all locations
// per flight
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
    public Flight[] getFlights2() {
        //First the method gets all flights. Than it iterates through the flights, and the
        // routs of flights. It adds the airport code-name of the route to a String ArrayList
        // if it is not yet in the list.
        //It converts the ArrayList to an array, and makes a paralell call to the weather API
        // with all locations at once.

        Flight[] flights = restTemplate.getForObject(url, Flight[].class);
        ArrayList <String> locations = new ArrayList<String>();
        for (Flight flight: flights){
            String[] cityName = flight.getRoute();
            for (int i=0; i<cityName.length;i++){
                if(!locations.contains(cityName[i])){
                    locations.add(cityName[i]);
                }
            }

        }
        String[] location = new String[locations.size()];
        List<Weather> weathers = weatherClient.getWeathers(locations.toArray(location));
        //It goes through all flights again, one by one, and checks each step of the route.
        // Then it iterates through the ArrayList of the weathers. If the airport code-name of
        // the flight route is the same as the airport code-name in the location of the weather in
        // the arraylist, it adds the weather to the weather array of the flight.
        for (Flight flight: flights){
            Weather[] flightWeather = flight.getWeather();
            flightWeather = new Weather[flight.getRoute().length];
            for (int i =0; i<flight.getRoute().length; i++ ) {
                for (Weather weather : weathers){
                    if (weather.getLocation().getLocationCode().equals(flight.getRoute()[i])){
                        flightWeather[i]=weather;
                        continue;
                    }
                }
            }
            flight.setWeather(flightWeather);
        }
        return flights;
    }
}

