package com.example.springcloudcircuitbreakerreading;


import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;

@Service
public class BookService {
    long count = 1;
    private static final Logger logger = LoggerFactory.getLogger(BookService.class);

    private RestTemplate restTemplate;

    public BookService() {

        this.restTemplate = new RestTemplate();

    }
    @CircuitBreaker(name = "bookListService",  fallbackMethod = "fallbackGetList")
    public String getList() {
        logger.info("calling regular method");
        logger.info(" count " + count);
        count++;
        ResponseEntity<String> data =  restTemplate.getForEntity("http://localhost:8090/recommended/", String.class);
        String result = data.getBody();
        return result;

    }

    public String fallbackGetList(Throwable th) {
        logger.info("Error making request to book service");
        logger.error("Error = " + th);
        return "Book Service is down";
    }

}