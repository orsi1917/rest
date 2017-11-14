package com.klm.dev.exercise.devcase02.flight;

import com.klm.dev.exercise.devcase02.weather.Weather;
import lombok.*;

import java.util.List;

@Builder
@Data
public class Flight {
    private int flightNumber;
    private String flightScheduleDate;
    private String id;
    @Singular("route")
    private List<String> route;
    @Singular("weather")
    private List<Weather> weather;
}
