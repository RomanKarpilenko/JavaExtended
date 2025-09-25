package ru.itmo.javaadvanced.lesson3.fibonacci.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.itmo.javaadvanced.lesson3.fibonacci.FibonacciHandler;

@Service
public class IterativeFibonacciHandler implements FibonacciHandler {
    private static final Logger log = LoggerFactory.getLogger(IterativeFibonacciHandler.class);

    @Override
    public Long fibonacci(Integer n) {
        log.info("Iterative fibonacci method is called");
        if (n < 0) {
            throw new IllegalArgumentException("Negative values is not supported");
        }
        if (n == 0) {
            return 0L;
        }
        if (n == 1) {
            return 1L;
        }
        int a = 0;
        int b = 1;

        for (int i = 2; i <= n; i++) {
            int c = a + b;
            a = b;
            b = c;
        }

        return (long) b;
    }
}
