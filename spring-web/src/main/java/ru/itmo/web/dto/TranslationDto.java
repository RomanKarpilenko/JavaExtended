package ru.itmo.web.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TranslationDto {
    @Size(min = 1, max = 10)
    private String languageCode;

    @Size(min = 1, max = 255)
    private String value;
}
