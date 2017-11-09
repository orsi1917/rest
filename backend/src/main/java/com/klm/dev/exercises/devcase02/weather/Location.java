package com.klm.dev.exercises.devcase02.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class Location {
    private String name;
//Original API can take requests based on airport code, but the JSON it returns does not contain
// a field for such code. The name for the airport in the flight status API and the weather API
// is different. To keep locations comparable a locationCode field was added here.
    private String locationCode;
    private String region;
    private String country;
    private String localtime;

}
