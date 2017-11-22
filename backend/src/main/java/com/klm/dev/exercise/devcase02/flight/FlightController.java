package com.klm.dev.exercise.devcase02.flight;

import com.klm.dev.exercise.devcase02.flight.model.backend.Flight;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/flights")
@RestController
public class FlightController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private FlightService flightService;

    @CrossOrigin
    @RequestMapping( produces = "application/json", method = RequestMethod.GET)
    public List<Flight> getFlights() {
        return flightService.getFlightsWithWeather();
    }

}
