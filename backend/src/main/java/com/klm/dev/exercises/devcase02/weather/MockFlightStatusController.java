package com.klm.dev.exercises.devcase02.weather;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Configuration
@RestController
@PropertySource("FlightStatusAPI_Response.txt")
public class MockFlightStatusController {

    @Value("${value}")
    String flightstatus;


    @GetMapping("/flightstatus")
    public String getFlightStatus() {
        return  flightstatus;
    }
}
