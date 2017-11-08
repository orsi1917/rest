package com.klm.dev.exercises.devcase02.flight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlightController {
    @Autowired
    private FlightClient flightClient;

    @CrossOrigin
    @GetMapping("/flights")
    public Flight[] getFlights() {
        return flightClient.getFlights2();
    }

}
