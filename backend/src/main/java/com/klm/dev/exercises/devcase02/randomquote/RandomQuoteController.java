package com.klm.dev.exercises.devcase02.randomquote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@PropertySource("classpath:RandomQuote.properties")
public class RandomQuoteController {
    @Autowired
    private RandomQuoteClient randomQuoteClient;

    @CrossOrigin
    @GetMapping("/quote")
    public Quote getQuote() {
        return randomQuoteClient.getQuote();
    }

    @CrossOrigin
    @GetMapping ("/quotes")
    public List<Quote> getQuotes() {   return randomQuoteClient.getQuotes(); }

}