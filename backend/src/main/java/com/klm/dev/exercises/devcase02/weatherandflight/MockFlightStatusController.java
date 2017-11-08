package com.klm.dev.exercises.devcase02.weatherandflight;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

@Configuration
@RestController
@PropertySource("FlightStatusAPI_Response.txt")
public class MockFlightStatusController {

    @Value("${value}")
    String flightstatus;


    @GetMapping("/flightstatus")
    public String getFlightStatus() {

        return  flightstatus;
    }
}
