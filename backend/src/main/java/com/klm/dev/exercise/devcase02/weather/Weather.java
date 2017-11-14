package com.klm.dev.exercise.devcase02.weather;

import lombok.*;

@Builder
@Data
public class Weather {
    private Location location;
    private Current current;
}
