package com.klm.dev.exercise.devcase02.flight;

import com.klm.dev.exercise.devcase02.weather.backend.Weather;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;
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
