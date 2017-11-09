import com.klm.dev.exercises.devcase02.flight.Flight;
import com.klm.dev.exercises.devcase02.flight.FlightClient;
import com.klm.dev.exercises.devcase02.weather.Location;
import com.klm.dev.exercises.devcase02.weather.Weather;
import com.klm.dev.exercises.devcase02.weather.WeatherClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@Configuration
@TestPropertySource(locations = {"classpath:Test.properties"})
public class FlightClientTest {

    private FlightClient flightClient;

    private RestTemplate restTemplate;
    private WeatherClient weatherClient;

    @Value("${test.airportCode1}")
    private String airportCode1;
    @Value("${test.airportCode2}")
    private String airportCode2;

    @Before
    public void setup(){
        flightClient = new FlightClient();
        restTemplate = mock(RestTemplate.class);
        weatherClient = mock(WeatherClient.class);
        ReflectionTestUtils.setField(flightClient, "restTemplate", restTemplate);
        ReflectionTestUtils.setField(flightClient, "weatherClient", weatherClient);

    }

    @Test
    public void testGetFlights(){
        Flight flight = new Flight();
        String[] route = {airportCode1, airportCode2};
        flight.setRoute(route);
        Flight[] flights = {flight, flight};
        when(restTemplate.getForObject(anyString(), eq(Flight[].class))).thenReturn(flights);

        Weather weather1 = new Weather();
        Location location1 = new Location();
        location1.setLocationCode(airportCode1);
        weather1.setLocation(location1);
        Weather weather2 = new Weather();
        Location location2 = new Location();
        location2.setLocationCode(airportCode2);
        weather2.setLocation(location2);
        List<Weather> newweathers = new ArrayList<Weather>();
        newweathers.add(weather1);
        newweathers.add(weather2);
        Weather[] weatherarray= new Weather[ newweathers.size()];
        weatherarray= newweathers.toArray(weatherarray);
        when(weatherClient.getWeathers(Mockito.any(String[].class))).thenReturn(newweathers);
        Flight[] response = flightClient.getFlights2();
        verify(weatherClient).getWeathers(route);
        assertTrue(Arrays.deepEquals(flight.getWeather(), weatherarray));
    }

}