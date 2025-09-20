package ru.itmo.javaadvanced.lesson3.storage;

public interface Storage {
    void put(String key, Long value);
    Long get(String key);
    boolean contains(String key);
}
