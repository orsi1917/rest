package com.klm.dev.exercises.devcase02.weather;

import com.klm.dev.exercises.devcase02.randomquote.RandomQuoteCallable;
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
    private RestTemplate restTemplate = new RestTemplate();
    private static final int NTHREDS = 10;

    @Value("${url}")
    private String url;

    public Weather getWeather(String cityName) {
        return restTemplate.getForObject(url+cityName, Weather.class);
        }
    public List<Weather> getWeathers(String[] cityName) {
        ExecutorService executor = Executors.newFixedThreadPool(NTHREDS);
        List<Future<Weather>> list = new ArrayList<Future<Weather>>();
        List<Weather> weathers = new ArrayList<Weather>();
        for (int i = 0; i < cityName.length; i++) {
            Callable<Weather> worker = new WeatherCallable(restTemplate, url+cityName[i]);
            Future<Weather> submit = executor.submit(worker);
            list.add(submit);
        }
        Weather weather = new Weather();
        // now retrieve the result
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

