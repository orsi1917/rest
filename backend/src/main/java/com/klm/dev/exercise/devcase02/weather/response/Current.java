package com.klm.dev.exercise.devcase02.weather.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Current {
    @JsonProperty("last_updated")
    private String lastUpdated;
    @JsonProperty("temp_c")
    private Double temperatureInCelcius;
    @JsonProperty("temp_f")
    private  Double temperatureInFahrenheit;
    @JsonProperty("is_day")
    private Boolean isDay;
    private Condition condition;

}
