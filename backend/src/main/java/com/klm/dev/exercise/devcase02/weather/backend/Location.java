package com.klm.dev.exercise.devcase02.weather.backend;

import lombok.Builder;
import lombok.Data;

/**
 * <h1> Location </h1>
 * <p>
 * Location is an object inside weather.
 * Created to consume a weather API from APIXU.</p>
 * <p>
 * The Original API can take requests based on airport code, but the JSON it returns
 * does not contain a field for such code. The name for the airport in the flight
 * status API and the weather API is different. To keep locations comparable a
 * locationCode field was added here.</p>
 * @author  Orsi
 * @version 1.0
 */

@Builder
@Data
public class Location {
    private String name;
    private String locationCode;
    private String region;
    private String country;
    private String localtime;

}
