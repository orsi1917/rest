package com.klm.dev.exercise.devcase02.flight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/flights")
@RestController
public class FlightController {

    @Autowired
    private FlightService flightService;

    @CrossOrigin
    @GetMapping
    public List<Flight> getFlights() {
        return flightService.getFlightsWithWeather();
    }

}