package ru.itmo.javaadvanced.lesson3.storage.impl;

import ru.itmo.javaadvanced.lesson3.storage.Storage;

import java.util.HashMap;

public class InMemoryStorageImpl implements Storage {

    private final HashMap<String, Long> map = new HashMap<>();

    @Override
    public void put(String key, Long value) {
        map.put(key, value);
    }

    @Override
    public Long get(String key) {
        return map.get(key);
    }

    @Override
    public boolean contains(String key) {
        return map.containsKey(key);
    }
}
