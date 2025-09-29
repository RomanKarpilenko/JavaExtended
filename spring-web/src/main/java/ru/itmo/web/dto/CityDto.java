package ru.itmo.web.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CityDto {
    @Min(1)
    @Max(9007199254740991L)
    private Long id;

    @Size(min = 1, max = 255)
    private String code;

    @Min(0)
    @Max(9007199254740991L)
    private Long population;

    private TranslationKeyDto nameTranslationKey;
}
