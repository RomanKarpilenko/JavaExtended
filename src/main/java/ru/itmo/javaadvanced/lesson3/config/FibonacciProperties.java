package ru.itmo.javaadvanced.lesson3.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "fibonacci")
public class FibonacciProperties {
    private boolean cacheEnabled = true;
    private String method = "iterative";
}
