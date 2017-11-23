package com.klm.dev.exercise.devcase02.flight.model.response;

import com.klm.dev.exercise.devcase02.weather.model.response.Weather;
import lombok.*;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Flight {
    private int flightNumber;
    private String flightScheduleDate;
    private String id;
    @Singular("route")
    private List<String> route;
    @Singular("weather")
    private List<Weather> weather;
}
