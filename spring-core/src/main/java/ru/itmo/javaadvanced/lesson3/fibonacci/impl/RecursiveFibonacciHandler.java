package ru.itmo.javaadvanced.lesson3.fibonacci.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.itmo.javaadvanced.lesson3.fibonacci.FibonacciHandler;

public class RecursiveFibonacciHandler implements FibonacciHandler {
    private final static Logger log = LoggerFactory.getLogger(RecursiveFibonacciHandler.class);

    @Override
    public Long fibonacci(Integer n) {
        log.info("Recursive fibonacci method is called");
        if (n < 0) {
            throw new IllegalArgumentException("Negative values is not supported");
        } else if (n == 0) {
            return 0L;
        } else if (n == 1) {
            return 1L;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }
}
