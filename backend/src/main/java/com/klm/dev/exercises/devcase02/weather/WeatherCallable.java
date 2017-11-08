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
        Weather weather= new Weather();
        weather=  restTemplate.getForObject(url, Weather.class);
        Location location = new Location();
        String cityName=new String();
        cityName = url.substring(url.length()-3);
        location= weather.getLocation();
        location.setLocationCode(cityName);
        weather.setLocation(location);
        return weather;

    }
}
