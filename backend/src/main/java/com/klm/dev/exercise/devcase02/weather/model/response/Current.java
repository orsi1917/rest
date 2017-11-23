package com.klm.dev.exercise.devcase02.weather.model.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Current {
    private String lastUpdated;
    private Double temperatureInCelcius;
    private  Double temperatureInFahrenheit;
    private Boolean isDay;
    private Condition condition;
}
