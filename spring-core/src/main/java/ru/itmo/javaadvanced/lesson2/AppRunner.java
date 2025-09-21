package ru.itmo.javaadvanced.lesson2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.itmo.javaadvanced.lesson2.converter.Converter;
import ru.itmo.javaadvanced.lesson2.converter.impl.MonthToWordConverter;
import ru.itmo.javaadvanced.lesson2.converter.impl.WordToMonthConverter;
import ru.itmo.javaadvanced.lesson2.mapper.Mapper;
import ru.itmo.javaadvanced.lesson2.mapper.impl.MonthToWordMapper;
import ru.itmo.javaadvanced.lesson2.model.Month;

import java.util.List;

@SpringBootApplication
public class AppRunner {
    public static void main(String[] args) {
        try (ConfigurableApplicationContext context = SpringApplication.run(AppRunner.class, args)) {
            MonthToWordConverter monthToWordConverter = (MonthToWordConverter) context.getBean("monthToWordConverter", Converter.class);
            System.out.println(monthToWordConverter.convert(Month.AUGUST));
            WordToMonthConverter wordToMonthConverter = (WordToMonthConverter) context.getBean("wordToMonthConverter", Converter.class);
            System.out.println(wordToMonthConverter.convert("March"));
            MonthToWordMapper monthToWordMapper = (MonthToWordMapper) context.getBean("monthToWordMapper", Mapper.class);
            System.out.println(monthToWordMapper.map(List.of(Month.AUGUST, Month.MARCH)));
        }
    }
}
