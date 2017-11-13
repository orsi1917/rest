package com.klm.dev.exercises.devcase02.weather;

import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Callable;

public class WeatherCallable implements Callable<Weather> {
    private RestTemplate restTemplate;
    private String url;

    public WeatherCallable(RestTemplate restTemplate, String url) {
        this.restTemplate = restTemplate;
        this.url = url;
    }
    @Override
    public Weather call() throws Exception {
        Weather weather;
        weather=  restTemplate.getForObject(url, Weather.class);
        Location location = new Location();
        // The JSON from the weather API does not contain a field locationCode (airport code).
        // The weather API is called with the code-name of the airport.
        //  The name for the airport in the flight status API and name for the airport in
        // the weather API are different.
        // here the code adds the airport code-name to the location object in weather to keep it
        // comperable.
        String cityName=new String();
        // The call(); method is not allowed to have parameters. the code-name of the airport is
        // the last 3 letters in the url.
        cityName = url.substring(url.length()-3);
        location= weather.getLocation();
        location.setLocationCode(cityName);
        weather.setLocation(location);
        return weather;

    }
}
