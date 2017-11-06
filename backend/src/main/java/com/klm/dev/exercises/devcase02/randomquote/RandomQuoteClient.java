package com.klm.dev.exercises.devcase02.randomquote;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class RandomQuoteClient {
   private RestTemplate restTemplate = new RestTemplate();

   public Quote getQuote() {
        return restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
    }


}
