package ru.itmo.javaadvanced.lesson3.service;

import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import ru.itmo.javaadvanced.lesson3.fibonacci.FibonacciHandler;
import ru.itmo.javaadvanced.lesson3.storage.Storage;

@Service
@RequiredArgsConstructor
public class FibonacciService {
    private final FibonacciHandler fibonacciHandler;

    @Nullable
    private final Storage storage;

    public Long fibonacci(Integer n) {
        if (storage != null) {
            if (storage.contains(n.toString())) {
                return storage.get(n.toString());
            } else {
                Long result = fibonacciHandler.fibonacci(n);
                storage.put(n.toString(), result);
                return result;
            }
        } else {
            return fibonacciHandler.fibonacci(n);
        }
    }
}
