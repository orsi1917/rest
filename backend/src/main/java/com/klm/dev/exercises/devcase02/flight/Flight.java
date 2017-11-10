package com.klm.dev.exercises.devcase02.flight;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.klm.dev.exercises.devcase02.weather.Weather;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Flight {
    private int flightNumber;
    private String flightScheduleDate;
    private String id;
    List<String> route;
    List<Weather> weather;
}
