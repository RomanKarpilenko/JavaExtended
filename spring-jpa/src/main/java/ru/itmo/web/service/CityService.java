package ru.itmo.web.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itmo.web.model.City;
import ru.itmo.web.model.LanguageCode;
import ru.itmo.web.model.Translation;
import ru.itmo.web.repository.CityRepository;

import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityRepository cityRepository;

    @Transactional(readOnly = true)
    public String description(Long id) {
        City city = cityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Город с id %s не найден".formatted(id)));

        Map<String, String> translations = city.getNameTranslationKey()
                .getTranslations()
                .stream()
                .collect(Collectors.toMap(Translation::getLanguageCode, Translation::getValue));

        String ru = translations.getOrDefault(LanguageCode.RU.toString(), "");
        String en = translations.getOrDefault(LanguageCode.EN.toString(), "");

        return String.format(
                "city_id: %s\ncode: %s\nru: %s\nen: %s\npopulation: %s",
                city.getId(),
                city.getCode(),
                ru,
                en,
                city.getPopulation()
        );
    }
}
