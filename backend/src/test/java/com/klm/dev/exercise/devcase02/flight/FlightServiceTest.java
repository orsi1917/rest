package com.klm.dev.exercise.devcase02.flight;
import com.klm.dev.exercise.devcase02.flight.model.backend.Flight;
import com.klm.dev.exercise.devcase02.weather.model.backend.Weather;
import com.klm.dev.exercise.devcase02.weather.WeatherClient;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import java.util.List;
import java.util.Map;


import static org.mockito.Mockito.verify;

@TestPropertySource(locations = {"classpath:Test.properties"})
@RunWith(SpringJUnit4ClassRunner.class)
public class FlightServiceTest {


    Flight flight;
    List <Flight> flights;
    Map<String, Weather> weathers;
    Weather weather1;
    Weather weather2;



    @Value("${test.airportCode1}")
    private String airportCode1;
    @Value("${test.airportCode2}")
    private String airportCode2;

    @Mock
    private WeatherClient weatherClient;
    @Mock
    private FlightClient flightClient;
    @InjectMocks
    private FlightService flightService;

//    @Before
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//        flight = Flight.builder().route(airportCode1).route(airportCode2).build();
//        flights = new ArrayList<>();
//        flights.add(flight);
//        flights.add(flight);
//        when(flightClient.getFlights()).thenReturn(flights);
//
//        weathers = new HashMap<>();
//        weather1 = Weather.builder().location(Location.builder().locationCode(airportCode1).build()).build();
//        weather2 = Weather.builder().location(Location.builder().locationCode(airportCode2).build()).build();
//        weathers.put(airportCode1, weather1);
//        weathers.put(airportCode2, weather2);
//        when(weatherClient.getWeathers(Matchers.anyList())).thenReturn(weathers);
//    }
//
//    @Test
//    public void testgetFlights() {
//        List<Flight> response = flightService.getFlights();
//        assertThat(response)
//                .isNotEmpty()
//                .contains(flight)
//                .doesNotContainNull()
//                .hasSize(2);
//
//    }
//    @Test
//    public void getAllDestinations() {
//
//        List <Flight> response = flightService.getFlightsWithWeather();
//        assertThat(response)
//                .isNotEmpty()
//                .contains(flight)
//                .doesNotContainNull()
//                .hasSize(2);
//        assertThat(response.get(0).getWeather())
//                .isNotEmpty()
//                .contains(weather1)
//                .hasSize(2);
//        verify(flightClient).getFlights() ;
//        verify(weatherClient).getWeathers(Lists.newArrayList(airportCode1, airportCode2));
//
//    }
}
