package ru.itmo.web.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Map;

@Data
public class TranslationKeyDto {
    @Size(min = 1, max = 255)
    private String translationKey;
    private Map<String, String> translations;
}
