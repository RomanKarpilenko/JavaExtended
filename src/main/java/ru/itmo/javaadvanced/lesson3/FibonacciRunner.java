package ru.itmo.javaadvanced.lesson3;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.itmo.javaadvanced.lesson3.config.FibonacciConfiguration;
import ru.itmo.javaadvanced.lesson3.service.FibonacciService;

import java.time.Duration;
import java.time.Instant;
import java.util.InputMismatchException;
import java.util.Scanner;


@Component
@RequiredArgsConstructor
public class FibonacciRunner implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(FibonacciRunner.class);
    private final Scanner scanner;

    private final FibonacciConfiguration fibonacciConfiguration;
    private final FibonacciService fibonacciService;

    @Override
    public void run(String... args) {
        var isFirstRun = true;
        while (true) {
            if (isFirstRun) {
                log.info("""
                                === Fibonacci config ===
                                Is enabled cache: {}
                                Computing method used: {}
                                """,
                        fibonacciConfiguration.cacheEnabled(),
                        fibonacciConfiguration.fibonacciMethod()
                );
                log.info("=== Fibonacci logger ===");
            }
            isFirstRun = false;
            log.info("Please, enter the value that will be computed");
            if (!scanner.hasNext()) {
                break;
            }
            try {
                Integer value = scanner.nextInt();
                Instant start = Instant.now();
                Long fibonacci = fibonacciService.fibonacci(value);
                Instant end = Instant.now();
                Long duration = Duration.between(start, end).toNanos();
                log.info("Value {} was computed in {} nanoseconds", fibonacci, duration);
            } catch (InputMismatchException e) {
                log.error("Введенное значение не является целым положительным числом");
                break;
            } catch (Exception e) {
                log.error(e.getMessage());
                break;
            }
        }
    }
}
