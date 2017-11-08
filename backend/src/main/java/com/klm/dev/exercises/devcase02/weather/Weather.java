package com.klm.dev.exercises.devcase02.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather {
    private Location location;
    private Current current;
    private Float wind_mph;
    private String wind_kph;
    private Double feelslike_c;
    private Double feelslike_f;

}
