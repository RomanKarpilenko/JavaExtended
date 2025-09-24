package ru.itmo.javaadvanced.lesson3.config;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.itmo.javaadvanced.lesson3.fibonacci.FibonacciHandler;
import ru.itmo.javaadvanced.lesson3.fibonacci.impl.IterativeFibonacciHandler;
import ru.itmo.javaadvanced.lesson3.fibonacci.impl.RecursiveFibonacciHandler;
import ru.itmo.javaadvanced.lesson3.storage.Storage;
import ru.itmo.javaadvanced.lesson3.storage.impl.InMemoryStorageImpl;

import java.util.Scanner;

@Configuration
@EnableConfigurationProperties(FibonacciProperties.class)
@RequiredArgsConstructor
public class FibonacciConfiguration {
    private static final Logger log = LoggerFactory.getLogger(FibonacciConfiguration.class);
    private final FibonacciProperties fibonacciProperties;

    @Bean("cacheEnabled")
    public boolean cacheEnabled() {
        log.info("getIsCacheEnabled() is called");
        return fibonacciProperties.isCacheEnabled();
    }

    public FibonacciMethod fibonacciMethod() {
        log.info("getFibonacciMethod() is called");
        return FibonacciMethod.fromString(fibonacciProperties.getMethod());
    }

    @Bean
    FibonacciHandler fibonacciHandler() {
        log.info("getFibonacciHandler() is called");
        return switch (fibonacciMethod()) {
            case ITERATIVE -> new IterativeFibonacciHandler();
            case RECURSIVE -> new RecursiveFibonacciHandler();
        };
    }

    @Bean
    public Scanner getScanner() {
        log.info("getScanner is called");
        return new Scanner(System.in);
    }

    @Bean
    public Storage getStorage() {
        log.info("getStorage is called");
        if (fibonacciProperties.isCacheEnabled()) {
            return new InMemoryStorageImpl();
        } else {
            return null;
        }
    }
}
