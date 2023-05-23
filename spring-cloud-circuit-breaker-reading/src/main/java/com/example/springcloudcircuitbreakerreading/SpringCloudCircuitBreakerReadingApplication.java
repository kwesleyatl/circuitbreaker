package com.example.springcloudcircuitbreakerreading;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@SpringBootApplication
@RestController
public class SpringCloudCircuitBreakerReadingApplication {


	@Autowired
	private BookService bookService;

	@RequestMapping("/to-read")
	public Mono<String> toRead() {
		return bookService.getList();
	}

	@RequestMapping("/cb-read")
	public Mono<String> cbRead() {
		return bookService.cbReadingList();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudCircuitBreakerReadingApplication.class, args);
	}

}
