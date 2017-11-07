package com.klm.dev.exercises.devcase02.randomquote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RandomQuoteController {
    @Autowired
    private RandomQuoteClient randomQuoteClient;

    @CrossOrigin
    @GetMapping("/quote")
    public Quote getQuote() {
        return randomQuoteClient.getQuote();
    }

    @CrossOrigin
    @GetMapping ("/many")
    public Quote[] getQuotes() {   return randomQuoteClient.getQuotes(); }
//    {
//        Quote[] quotes= new Quote[2];
//        Quote q = new Quote();
//        quotes[0]=q;
//        quotes[1]=q;
//        return quotes;
//
//    }

}