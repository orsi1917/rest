package com.klm.dev.exercises.devcase02.randomquote;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Callable;

public class RandomQuoteCallable implements Callable<Quote> {

    private RestTemplate restTemplate;
    private String url;

    public RandomQuoteCallable(RestTemplate restTemplate, String url) {
        this.restTemplate = restTemplate;
        this.url = url;
    }

    @Override
    public Quote call() throws Exception {
       return restTemplate.getForObject(url, Quote.class);

    }
}
