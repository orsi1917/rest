package com.klm.dev.exercises.devcase02.weather;

import com.klm.dev.exercises.devcase02.executor.ExecutorHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

@Service
@Configuration
@PropertySource("classpath:weather.properties")
public class WeatherClient {

    @Value("${executor.CorePoolSize}")
    private int corePoolSize;
    @Value("${executor.MaxPoolSize}")
    private int maxPoolSize;
    @Value("${executor.QueueCapacity}")
    private int queueCapacity;
    @Value("${executor.KeepAliveSeconds}")
    private int keepAliveSeconds;

    @Autowired
    private RestTemplate restTemplate;


    @Value("${url}")
    private String url;

    public Weather getWeather(String cityName) {
        Weather weather;
        weather = restTemplate.getForObject(url + cityName, Weather.class);
        weather.getLocation().setLocationCode(cityName);
        return weather;
        // The JSON from the weather API does not contain a field locationCode (airport code).
        // The weather API is called with the code-name of the airport.
        //  The name for the airport in the flight status API and name for the airport in
        // the weather API are different.
        // here the code adds the airport code-name to the location object in weather to keep it
        // comperable.
    }

    public Map<String, Weather> getWeathers(List<String> cityName) {
        ThreadPoolTaskExecutor executor = ExecutorHandler.getConfiguredThreadPoolTaskExecutor(corePoolSize, maxPoolSize, queueCapacity, keepAliveSeconds);
        List<Future<Weather>> list = new ArrayList<Future<Weather>>();
        Map<String, Weather> mapLocationToWeather = new LinkedHashMap<>();

        cityName.forEach(location -> {
            Callable<Weather> worker = new WeatherCallable(restTemplate, url + location);
            Future<Weather> submit = executor.submit(worker);
            list.add(submit);
        });
        list.forEach(future -> {
            try {
                Weather weather = future.get();
                String location = weather.getLocation().getLocationCode();
                mapLocationToWeather.put(location, weather);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        });
        return mapLocationToWeather;

    }
}

