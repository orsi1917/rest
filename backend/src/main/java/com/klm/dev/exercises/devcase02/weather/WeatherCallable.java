package com.klm.dev.exercises.devcase02.weather;

import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Callable;

public class WeatherCallable implements Callable<Weather> {
    private RestTemplate restTemplate;
    private String url;

    public WeatherCallable(RestTemplate restTemplate, String url) {
        this.restTemplate = restTemplate;
        this.url = url;
    }
    @Override
    public Weather call() throws Exception {
        return restTemplate.getForObject(url, Weather.class);

    }
}
