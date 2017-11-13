package com.klm.dev.exercises.devcase02;


import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(locations = {"classpath:Test.properties"})
public class FlightClientTest {

    @Value("${test.airportCode1}")
    private String airportCode1;
    @Value("${test.airportCode2}")
    private String airportCode2;

}
