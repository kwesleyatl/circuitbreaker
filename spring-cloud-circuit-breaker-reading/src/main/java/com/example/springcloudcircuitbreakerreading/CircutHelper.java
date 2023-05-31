package com.example.springcloudcircuitbreakerreading;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeoutException;

public class CircutHelper {
    private CircuitBreakerRegistry registry;

    public CircutHelper() {
        // Create a custom configuration for a CircuitBreaker
        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
//                .failureRateThreshold(5)
//                .waitDurationInOpenState(Duration.ofMillis(1000))
//                .permittedNumberOfCallsInHalfOpenState(2)
//                .slidingWindowSize(2)
//                .recordExceptions(IOException.class, TimeoutException.class)
//                .ignoreExceptions(BusinessException.class, OtherBusinessException.class)
//                .build();

                // the circuitbreaker will open if 70% of the last 10 calls failed
                .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
                .slidingWindowSize(10)
                .failureRateThreshold(70.0f)
                .build();

// Create a CircuitBreakerRegistry with a custom global configuration
        this.registry = CircuitBreakerRegistry.of(circuitBreakerConfig);
    }

    public CircuitBreaker getCircuitBreaker(String name)    {
        return  this.registry.circuitBreaker(name);

    }

}
