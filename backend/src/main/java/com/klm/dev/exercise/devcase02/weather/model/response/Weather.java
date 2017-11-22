package com.klm.dev.exercise.devcase02.weather.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Weather {
    private Location location;
    private Current current;

}