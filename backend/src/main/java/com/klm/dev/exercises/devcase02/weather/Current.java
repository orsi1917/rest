package com.klm.dev.exercises.devcase02.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Current {
    private String last_updated;
    private Double temp_c;
    private  Double temp_f;
    private Boolean is_day;
    private Condition condition;
    private Double wind_mph;
    private Double wind_kph;
    private Double feelslike_c;
    private Double feelslike_f;
}
