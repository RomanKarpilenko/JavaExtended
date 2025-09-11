package ru.itmo.javaadvanced.lesson2.converter.impl;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import ru.itmo.javaadvanced.lesson2.converter.Converter;
import ru.itmo.javaadvanced.lesson2.model.Month;

@Component(value = "wordToMonthConverter")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WordToMonthConverter implements Converter<String, Month> {
    @Override
    public Month convert(String input) {
        return switch (input) {
            case "January" -> Month.JANUARY;
            case "February" -> Month.FEBRUARY;
            case "March" -> Month.MARCH;
            case "April" -> Month.APRIL;
            case "May" -> Month.MAY;
            case "June" -> Month.JUNE;
            case "July" -> Month.JULY;
            case "August" -> Month.AUGUST;
            case "September" -> Month.SEPTEMBER;
            case "October" -> Month.OCTOBER;
            case "November" -> Month.NOVEMBER;
            case "December" -> Month.DECEMBER;
            case null, default -> null;
        };
    }
}
