package com.klm.dev.exercises.devcase02.randomquote;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

@Service
@Configuration
@PropertySource("classpath:randomQuote.properties")

public class RandomQuoteClient {

    private RestTemplate restTemplate = new RestTemplate();
    private static final int NTHREDS = 10;

    @Value("${quote.url}")
    private String url;

    @Value("${repeat}")
    private int repeat;

    public Quote getQuote() {
        return restTemplate.getForObject(url, Quote.class);
    }

    public List<Quote> getQuotes() {
        ExecutorService executor = Executors.newFixedThreadPool(NTHREDS);
        List<Future<Quote>> list = new ArrayList<Future<Quote>>();
        List<Quote> quotes = new ArrayList<Quote>();
        for (int i = 0; i < repeat; i++) {
            Callable<Quote> worker = new RandomQuoteCallable(restTemplate, url);
            Future<Quote> submit = executor.submit(worker);
            list.add(submit);
        }
        Quote quote = new Quote();
        // now retrieve the result
        for (Future<Quote> future : list) {
            try {
                quote = future.get();
                quotes.add(quote);
                System.out.print(quotes.size());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        return quotes;

    }
}




