package com.klm.dev.exercise.devcase02.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Builder
@Data
public class Condition {
    private String text;
    private String icon;
}
