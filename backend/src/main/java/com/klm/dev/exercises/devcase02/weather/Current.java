package com.klm.dev.exercises.devcase02.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Current {
    private String last_updated;
    private Double temp_c;
    private  Double temp_f;
    private Boolean is_day;
    private Condition condition;
}
