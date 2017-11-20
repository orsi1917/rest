package com.klm.dev.exercise.devcase02.weather;

import com.klm.dev.exercise.devcase02.executor.ExecutorHandler;
import lombok.extern.slf4j.Slf4j;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;


import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@Service
@Configuration
@Slf4j
@PropertySource("classpath:weather.properties")
public class WeatherClient {


    @Value("${executor.corePoolSize}")
    private int corePoolSize;
    @Value("${executor.maxPoolSize}")
    private int maxPoolSize;
    @Value("${executor.queueCapacity}")
    private int queueCapacity;
    @Value("${executor.keepAliveSeconds}")
    private int keepAliveSeconds;

    @Autowired
    WeatherClientProxy weatherClientProxy;


    public Weather getWeather(String cityName) {
        return weatherClientProxy.getWeather(cityName);
     }

    public Map<String, Weather> getWeathers(List<String> cityName) {
        ThreadPoolTaskExecutor executor = ExecutorHandler.getConfiguredThreadPoolTaskExecutor(corePoolSize, maxPoolSize, queueCapacity, keepAliveSeconds);
        List<Future<Weather>> listOfFutureWeathers = cityName.stream()
                .map(location -> {
                  Future<Weather> submit2 = executor.submit(() -> weatherClientProxy.getWeather(location));
                    return submit2;
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
        } catch (InterruptedException e) {
            log.error("Logged error message", e);
        } catch (ExecutionException e) {
            log.error(String.format("Could not retrieve weather for: %s", "given station"), e);
        }
        return weather;
    }
}

