package com.klm.dev.exercise.devcase02.weather;

import com.klm.dev.exercise.devcase02.executor.ExecutorHandler;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 *     The JSON from the weather API does not contain a field locationCode (airport code).
 The weather API is called with the code-name of the airport.
 The name for the airport in the flight status API and name for the airport in
 the weather API are different.
 here the code adds the airport code-name to the location object in weather to keep it
 comperable.
 */
@Service
@Configuration
@Slf4j
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

    // TODO intorduce caching
    public Weather getWeather(String cityName) {
        Weather weather;
        weather = restTemplate.getForObject(url + cityName, Weather.class);
        weather.getLocation().setLocationCode(cityName);
        return weather;
     }

    public Map<String, Weather> getWeathers(List<String> cityName) {
        ThreadPoolTaskExecutor executor = ExecutorHandler.getConfiguredThreadPoolTaskExecutor(corePoolSize, maxPoolSize, queueCapacity, keepAliveSeconds);
        List<Future<Weather>> listOfFutureWeathers = cityName.stream()
                .map(location -> {
                    Callable<Weather> worker = new WeatherCallable(restTemplate, url + location);
                    Future<Weather> submit = executor.submit(worker);
                    return submit;
                })
                .collect(Collectors.toList());

        Map<String, Weather> mapLocationToWeather = listOfFutureWeathers.stream()
                .map(future -> getWeather(future))
                .filter(weather -> Objects.nonNull(weather))
                .collect(Collectors.toMap(weather -> weather.getLocation().getLocationCode(), weather -> weather));
        return mapLocationToWeather;

    }

    private Weather getWeather(Future<Weather> future) {
        Weather weather = null;
        try {
            weather = future.get();
            // TODO ik moet dit even uitzoeken waarom ik dit toegevoegd heb
            String location = weather.getLocation().getLocationCode();
        } catch (InterruptedException e) {
            log.error("Logged error message", e);
        } catch (ExecutionException e) {
            log.error(String.format("Could not retrieve weather for: %s", "given station"), e);
        }
        return weather;
    }


}
