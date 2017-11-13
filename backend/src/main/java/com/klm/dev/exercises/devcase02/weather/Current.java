package com.klm.dev.exercises.devcase02.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
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
    @JsonProperty("wind_mph")
    private Double windMph;
    @JsonProperty("wind_kph")
    private Double windKmph;
    @JsonProperty("feelslike_c")
    private Double feelsLikeInCelsius;
    @JsonProperty("feelslike_f")
    private Double feelsLikeInFahrenheid;
}
