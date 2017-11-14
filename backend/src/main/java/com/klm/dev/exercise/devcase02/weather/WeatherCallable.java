package com.klm.dev.exercise.devcase02.weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Callable;

/**
 *
 *    <p>The JSON from the weather API does not contain a field locationCode (airport code).
 The weather API is called with the code-name of the airport.
 The name for the airport in the flight status API and name for the airport in
 the weather API are different.
 here the code adds the airport code-name to the location object in weather to keep it
 comperable.</p>
 <p>The call(); method is not allowed to have parameters. the code-name of the airport is
 the last 3 letters in the url.</p>
 *
 *
 */

public class WeatherCallable implements Callable<Weather> {
    @Autowired
    private RestTemplate restTemplate;
    private String url;

    public WeatherCallable(RestTemplate restTemplate, String url) {
        this.restTemplate = restTemplate;
        this.url = url;
    }

    @Override
    public Weather call() throws Exception {
        Weather weather;
        weather = restTemplate.getForObject(url, Weather.class);
        String cityName = url.substring(url.length() - 3);
        weather.getLocation().setLocationCode(cityName);
        return weather;

    }
}
