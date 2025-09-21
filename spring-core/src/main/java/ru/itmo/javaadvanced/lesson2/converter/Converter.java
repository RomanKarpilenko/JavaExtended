package ru.itmo.javaadvanced.lesson2.converter;

public interface Converter<I, O> {
    O convert(I input);
}
