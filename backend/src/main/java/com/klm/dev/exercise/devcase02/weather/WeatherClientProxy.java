package com.klm.dev.exercise.devcase02.weather;

import com.klm.dev.exercise.devcase02.weather.model.backend.Weather;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Configuration
@Slf4j
@PropertySource("classpath:weather.properties")
/**
 * This class is added to the application to enable chaching the data from the weather
 * API. Internal calls within the same class ignore the @Cacheable annotation, therefore
 * the getWeather method needs to be called through another class.
 */

public class WeatherClientProxy {


    @Autowired
    private RestTemplate restTemplate;

    @Value("${url}")
    private String url;
    /**
     *     The JSON from the weather API does not contain a field locationCode (airport code).
     The weather API is called with the code-name of the airport.
     The name for the airport in the flight status API and name for the airport in
     the weather API are different.
     here the code adds the airport code-name to the location object in weather to keep it
     comperable.
     */
    @Cacheable(value = "cityName")
    public Weather getWeather(String cityName) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json;version=com.klm.dev.exercise.devcase02.v2");
        HttpEntity<Weather> request = new HttpEntity<>(headers);
        ResponseEntity<Weather> response = restTemplate.exchange(url+ cityName, HttpMethod.GET, request , Weather.class);
        Weather weather = response.getBody();
        weather.getLocation().setLocationCode(cityName);
        log.info(" got it from API: " + weather.getLocation().getLocationCode());
        return weather;
    }

}
