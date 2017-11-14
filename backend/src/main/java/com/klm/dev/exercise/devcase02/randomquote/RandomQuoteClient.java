package com.klm.dev.exercise.devcase02.randomquote;

import com.klm.dev.exercise.devcase02.executor.ExecutorHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

@Service
@Configuration
@PropertySource("classpath:randomQuote.properties")

public class RandomQuoteClient {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${executor.CorePoolSize}")
    private int corePoolSize;
    @Value("${executor.MaxPoolSize}")
    private int maxPoolSize;
    @Value("${executor.QueueCapacity}")
    private int queueCapacity;
    @Value("${executor.KeepAliveSeconds}")
    private int keepAliveSeconds;

    @Value("${quote.url}")
    private String url;

    @Value("${repeat}")
    private int repeat;

    public Quote getQuote() {
        return restTemplate.getForObject(url, Quote.class);
    }

    public List<Quote> getQuotes() {
        ThreadPoolTaskExecutor executor = ExecutorHandler.getConfiguredThreadPoolTaskExecutor(corePoolSize, maxPoolSize, queueCapacity, keepAliveSeconds);
        List<Future<Quote>> list = new ArrayList<Future<Quote>>();
        List<Quote> quotes = new ArrayList<Quote>();
        IntStream.range(0, repeat).parallel().forEach($ -> {
            Callable<Quote> worker = new RandomQuoteCallable(restTemplate, url);
            Future<Quote> submit = executor.submit(worker);
            list.add(submit);
        });
        list.forEach(future -> {
            try {
                Quote quote = future.get();
                quotes.add(quote);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
        return quotes;

    }
}