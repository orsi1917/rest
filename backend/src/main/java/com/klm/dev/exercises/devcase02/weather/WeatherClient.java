package com.klm.dev.exercises.devcase02.weather;

import com.klm.dev.exercises.devcase02.randomquote.RandomQuoteCallable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Service
@Configuration
@PropertySource("classpath:Weather.properties")
public class WeatherClient {
    @Autowired
    private RestTemplate restTemplate;
    private static final int NTHREDS = 30;

    @Value("${url}")
    private String url;

    public Weather getWeather(String cityName) {
        Weather weather= new Weather();
        weather= restTemplate.getForObject(url+cityName, Weather.class);
        Location location = new Location();
        location= weather.getLocation();
        // The JSON from the weather API does not contain a field locationCode (airport code).
        // The weather API is called with the code-name of the airport.
       //  The name for the airport in the flight status API and name for the airport in
        // the weather API are different.
        // here the code adds the airport code-name to the location object in weather to keep it
        // comperable.
        location.setLocationCode(cityName);
        weather.setLocation(location);
        return weather;
        }
    public List<Weather> getWeathers(List<String> cityName) {
        ExecutorService executor = Executors.newFixedThreadPool(NTHREDS);
        List<Future<Weather>> list = new ArrayList<Future<Weather>>();
        List<Weather> weathers = new ArrayList<Weather>();
        for (int i = 0; i < cityName.size(); i++) {
            Callable<Weather> worker = new WeatherCallable(restTemplate, url+cityName.get(i));
            Future<Weather> submit = executor.submit(worker);
            list.add(submit);
        }
        Weather weather = new Weather();
           for (Future<Weather> future : list) {
            try {
                weather = future.get();
                weathers.add(weather);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        return weathers;

    }
}

