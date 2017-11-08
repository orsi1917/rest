package com.klm.dev.exercises.devcase02.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Location {
    private String name;
    private String locationCode;
    private String region;
    private String country;
    private String localtime;

}
