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

import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@Configuration
@PropertySource("classpath:randomQuote.properties")

public class RandomQuoteClient {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${executor.corePoolSize}")
    private int corePoolSize;
    @Value("${executor.maxPoolSize}")
    private int maxPoolSize;
    @Value("${executor.queueCapacity}")
    private int queueCapacity;
    @Value("${executor.keepAliveSeconds}")
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
        List<Future<Quote>> list =
                IntStream.range(0, repeat)
                        .mapToObj($ -> {
                            Callable<Quote> worker = new RandomQuoteCallable(restTemplate, url);
                            Future<Quote> submit = executor.submit(worker);
                            return submit;
                        })
                        .collect(Collectors.toList());
        List<Quote> quotes = list.stream()
                .map(future -> getQuote(future))
                .filter(quote -> Objects.nonNull(quote))
                .collect(Collectors.toList());
        return quotes;

    }

    private Quote getQuote(Future<Quote> future) {
        Quote quote = null;
        try {
            quote = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return quote;
    }
}