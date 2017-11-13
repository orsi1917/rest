package com.klm.dev.exercises.devcase02.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather {
    private Location location;
    private Current current;


}
