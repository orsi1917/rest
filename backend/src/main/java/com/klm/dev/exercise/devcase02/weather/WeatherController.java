package com.klm.dev.exercise.devcase02.weather;

import com.klm.dev.exercise.devcase02.Error.ErrorObject;
import com.klm.dev.exercise.devcase02.version.ApiVersion;
import org.modelmapper.ModelMapper;

import com.klm.dev.exercise.devcase02.weather.model.response.Weather;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@Slf4j
@RestController
public class WeatherController {
    @Autowired
    private WeatherClient weatherClient;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * <p>
     *
     * @param cityName Needs to be a 3-letter airport-code. Other requests are refused with error code 406</p>
     *                 <p>
     * @param version  Default is the latest version. To specify version, send a requestheader with
     *                 Accept = "application/json;version=com.klm.dev.exercise.devcase02.v1" <br>
     *                 V1 returns wind information, V2 does not.
     *                 </p>
     * @return returns weather information for given airport.
     */
    @CrossOrigin
    @RequestMapping(value = "/weather/{cityName}", produces = "application/json", method = RequestMethod.GET)
    public ResponseEntity<Object> getWeather(@PathVariable String cityName, @RequestHeader(value = "Accept") ApiVersion version) {

        if (cityName.length() != 3) {
            return new ResponseEntity(new ErrorObject(406, "Invalid airport code. Please use a three-letter airport code, such as: AMS (Amsterdam)"), HttpStatus.NOT_ACCEPTABLE);
        }
        com.klm.dev.exercise.devcase02.weather.model.backend.Weather weather = weatherClient.getWeather(cityName);
        if (Objects.isNull(weather)){
            return new ResponseEntity(new ErrorObject(400, "Internal Server Error: airport code does not exsist"), HttpStatus.BAD_REQUEST);
        }
        if (version.equals(ApiVersion.V1)) {
            return new ResponseEntity(weather, HttpStatus.OK);
        } else {
            return new ResponseEntity(changeModel(weather), HttpStatus.OK);
        }
    }

    private Weather changeModel(com.klm.dev.exercise.devcase02.weather.model.backend.Weather weather) {
        return modelMapper.map(weather, Weather.class);
    }
}

