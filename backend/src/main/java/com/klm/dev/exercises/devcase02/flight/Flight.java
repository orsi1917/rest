package com.klm.dev.exercises.devcase02.flight;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.klm.dev.exercises.devcase02.weather.Weather;
import lombok.*;

import java.util.List;

@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Flight {
    private int flightNumber;
    private String flightScheduleDate;
    private String id;
    @Singular("route")
    private List<String> route;
    @Singular("weather")
    private List<Weather> weather;
}
