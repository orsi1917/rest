package com.klm.dev.exercises.devcase02.weather;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Configuration
@PropertySource("classpath:Weather.properties")
public class WeatherClient {
    private RestTemplate restTemplate = new RestTemplate();
    private static final int NTHREDS = 10;

    @Value("${url}")
    private String url;

    public Weather getWeather(String cityName) {
        return restTemplate.getForObject(url+cityName, Weather.class);
    }
}
