package ru.itmo.spring_database.model;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class City {
    private Long id;
    private String code;
    private String nameTranslationKey;
    private Long population;
}
