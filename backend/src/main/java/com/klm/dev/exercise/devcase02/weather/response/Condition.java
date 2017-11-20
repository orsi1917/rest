package com.klm.dev.exercise.devcase02.weather.response;
import lombok.Builder;
import lombok.Data;


@Data
public class Condition {
    private String text;
    private String icon;
}
