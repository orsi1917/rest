package com.klm.dev.exercise.devcase02.weather;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Condition {
    private String text;
    private String icon;
}
