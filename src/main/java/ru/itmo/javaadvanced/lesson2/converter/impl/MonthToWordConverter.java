package ru.itmo.javaadvanced.lesson2.converter.impl;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import ru.itmo.javaadvanced.lesson2.converter.Converter;
import ru.itmo.javaadvanced.lesson2.model.Month;

@Component(value = "monthToWordConverter")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MonthToWordConverter implements Converter<Month, String> {

    @Override
    public String convert(Month input) {
        return switch (input) {
            case JANUARY -> "January";
            case FEBRUARY -> "February";
            case MARCH -> "March";
            case APRIL -> "April";
            case MAY -> "May";
            case JUNE -> "June";
            case JULY -> "July";
            case AUGUST -> "August";
            case SEPTEMBER -> "September";
            case OCTOBER -> "October";
            case NOVEMBER -> "November";
            case DECEMBER -> "December";
        };
    }
}
