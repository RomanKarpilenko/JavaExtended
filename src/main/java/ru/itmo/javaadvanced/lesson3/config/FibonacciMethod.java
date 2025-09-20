package ru.itmo.javaadvanced.lesson3.config;

import java.util.Arrays;

public enum FibonacciMethod {
    ITERATIVE,
    RECURSIVE;

    static FibonacciMethod fromString(String method) {
        return Arrays.stream(FibonacciMethod.values()).filter(
                        value -> value.name().equalsIgnoreCase(method)
                )
                .findFirst()
                .orElse(null);
    }
}
