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
import java.util.Arrays;

@Service
@Configuration
@PropertySource("classpath:RandomQuote.properties")
public class RandomQuoteClient {

   private RestTemplate restTemplate = new RestTemplate();

    @Value("${url}")
    private String url;

   public Quote getQuote() {
        return restTemplate.getForObject(url, Quote.class);
    }


}
