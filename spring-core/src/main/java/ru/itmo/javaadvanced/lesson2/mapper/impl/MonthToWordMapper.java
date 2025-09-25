package ru.itmo.javaadvanced.lesson2.mapper.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.itmo.javaadvanced.lesson2.converter.Converter;
import ru.itmo.javaadvanced.lesson2.mapper.Mapper;
import ru.itmo.javaadvanced.lesson2.model.Month;

import java.util.List;

@Component(value = "monthToWordMapper")
@RequiredArgsConstructor
public class MonthToWordMapper implements Mapper<Month, String> {
    private final Converter<Month, String> converter;

    @Override
    public List<String> map(List<Month> input) {
        return input.stream().map(converter::convert).toList();
    }
}
