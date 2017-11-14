package com.klm.dev.exercise.devcase02.flight;


import com.klm.dev.exercise.devcase02.weather.Location;
import com.klm.dev.exercise.devcase02.weather.Weather;
import com.klm.dev.exercise.devcase02.weather.WeatherClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.*;

@TestPropertySource(locations = {"classpath:Test.properties"})
@RunWith(SpringJUnit4ClassRunner.class)
public class FlightClientTest {

    @Value("${test.airportCode1}")
    private String airportCode1;
    @Value("${test.airportCode2}")
    private String airportCode2;

    @Mock
    private RestTemplate restTemplate;
   @InjectMocks
    private FlightClient flightClient;


    @Before
    public void setUp() { MockitoAnnotations.initMocks(this); }
    @Test
    public void testgetFlights() {
        Flight flight = Flight.builder().route("AMS").route("JFK").build();
        Flight[] flights = {flight, flight};
        when(restTemplate.getForObject(anyString(), eq(Flight[].class))).thenReturn(flights);
        List<Flight> response = flightClient.getFlights();
        verify(restTemplate).getForObject(anyString(), eq(Flight[].class));
        assertThat(response)
                .isNotEmpty()
                .contains(flight)
                .doesNotContainNull()
                .hasSize(2);
    }
 }
