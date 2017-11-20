package com.klm.dev.exercise.devcase02;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
@EnableCaching
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

   @Bean
  public CacheManager cacheManager() {
        return new EhCacheCacheManager(ehCache().getObject());
    }

    @Bean
    public EhCacheManagerFactoryBean ehCache() {
        return new EhCacheManagerFactoryBean();
    }


    //TODO add monitoring by saving the amount of calls that has been executed for each endpoint and each api call with date time of call,
    // saving the monitor data in mongo database with java
    // and exposing this data trough a new endpoint



}
