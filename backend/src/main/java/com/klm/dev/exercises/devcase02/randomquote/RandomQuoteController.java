package com.klm.dev.exercises.devcase02.randomquote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quotes")
@PropertySource("classpath:RandomQuote.properties")
public class RandomQuoteController {
    @Autowired
    private RandomQuoteClient randomQuoteClient;

    @CrossOrigin
    @RequestMapping(value = "1", method = RequestMethod.GET)
    public Quote getQuote() {
        return randomQuoteClient.getQuote();
    }

    @CrossOrigin
    @GetMapping
    public List<Quote> getQuotes() {   return randomQuoteClient.getQuotes(); }

}