package com.klm.dev.exercises.devcase02.flight;

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

    @Value("${flights.url}")
    private String url;

    public Flight[] getFlights() {
        return restTemplate.getForObject(url, Flight[].class);
    }
}

