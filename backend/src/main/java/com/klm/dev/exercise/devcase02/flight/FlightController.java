package com.klm.dev.exercise.devcase02.flight;

import com.klm.dev.exercise.devcase02.flight.model.backend.Flight;
import com.klm.dev.exercise.devcase02.version.ApiVersion;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RequestMapping("/flights")
@RestController
public class FlightController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private FlightService flightService;

    @CrossOrigin
    @RequestMapping(produces = "application/json", method = RequestMethod.GET)
    public ResponseEntity<Object> getFlights(@RequestHeader(value = "Accept") ApiVersion version) {
        if (version.equals(ApiVersion.V1)) {
          return new ResponseEntity(flightService.getFlightsWithWeather(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity(changeModel(flightService.getFlightsWithWeather()), HttpStatus.OK);
        }
    }

    private List<com.klm.dev.exercise.devcase02.flight.model.response.Flight> changeModel(List<Flight> flights) {
        List <com.klm.dev.exercise.devcase02.flight.model.response.Flight> newFlights =flights.stream()
                .map(flight -> modelMapper.map(flight, com.klm.dev.exercise.devcase02.flight.model.response.Flight.class))
                .collect(Collectors.toList() ) ;
        return newFlights;
    }
}
