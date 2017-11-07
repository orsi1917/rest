package com.klm.dev.exercises.devcase02.randomquote;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Callable;

@Configuration
@PropertySource("classpath:RandomQuote.properties")
public class RandomQuoteCallable implements Callable<Quote> {

    private RestTemplate restTemplate = new RestTemplate();

    @Value("${url}")
    private String url;

    @Override
    public Quote call() throws Exception {
        return restTemplate.getForObject(url, Quote.class);

    }
}
