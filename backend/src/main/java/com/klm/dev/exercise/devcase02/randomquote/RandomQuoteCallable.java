package com.klm.dev.exercise.devcase02.randomquote;

import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Callable;
// TODO JL have a look at the callable class you can have it as anonymous class in the calling submit or even a method
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