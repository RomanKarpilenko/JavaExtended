package ru.itmo.javaadvanced.lesson2.mapper;

import java.util.List;

public interface Mapper<I, O> {
    List<O> map(List<I> input);
}
