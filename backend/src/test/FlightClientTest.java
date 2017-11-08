import com.klm.dev.exercises.devcase02.flight.Flight;
import com.klm.dev.exercises.devcase02.flight.FlightClient;
import com.klm.dev.exercises.devcase02.weather.WeatherClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FlightClientTest {

    private FlightClient flightClient;

    private RestTemplate restTemplate;

    private WeatherClient weatherClient;

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
        String[] route = {"AMS", "JFK"};
        flight.setRoute(route);
        Flight[] flights = {flight};
        when(restTemplate.getForObject(anyString(), eq(Flight[].class))).thenReturn(flights);
        Flight[] response = flightClient.getFlights2();
        verify(weatherClient).getWeathers(route);
    }

}