package com.klm.dev.exercise.devcase02.flight;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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

    Flight flight;
    Flight[] flights;

    @Value("${test.airportCode1}")
    private String airportCode1;
    @Value("${test.airportCode2}")
    private String airportCode2;

    @Mock
    private RestTemplate restTemplate;
    @InjectMocks
    private FlightClient flightClient;


    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);
       flight = Flight.builder().route(airportCode1).route(airportCode2).build();
       flights = new Flight[]{flight, flight};
        when(restTemplate.getForObject(anyString(), eq(Flight[].class))).thenReturn(flights);
    }

    @Test
    public void getFlights_ok() {
        List<Flight> response = flightClient.getFlights();

        verify(restTemplate).getForObject(anyString(), eq(Flight[].class));
        assertThat(response)
                .isNotEmpty()
                .contains(flight)
                .doesNotContainNull()
                .hasSize(2);
    }
}
