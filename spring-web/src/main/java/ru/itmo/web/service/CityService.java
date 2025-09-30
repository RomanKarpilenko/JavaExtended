package ru.itmo.web.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itmo.web.dto.CityDto;
import ru.itmo.web.dto.DescriptionDto;
import ru.itmo.web.exception.CityNotFoundException;
import ru.itmo.web.mapper.CityMapper;
import ru.itmo.web.mapper.TranslationKeyMapper;
import ru.itmo.web.model.City;
import ru.itmo.web.model.LanguageCode;
import ru.itmo.web.model.Translation;
import ru.itmo.web.repository.CityRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityRepository cityRepository;
    private final CityMapper cityMapper;
    private final TranslationKeyMapper translationKeyMapper;

    @Transactional
    public void createCity(CityDto dto) {
        cityRepository.save(cityMapper.toEntity(dto));
    }

    @Transactional
    public void updateCity(Long id, CityDto dto) {
        cityRepository.findById(id).ifPresent(
                city -> {
                    city.setCode(dto.getCode());
                    city.setPopulation(dto.getPopulation());
                    city.setNameTranslationKey(translationKeyMapper.toEntity(dto.getNameTranslationKey()));
                }
        );
    }

    @Transactional(readOnly = true)
    public DescriptionDto description(Long id) {
        City city = cityRepository.findById(id)
                .orElseThrow(() -> new CityNotFoundException(id.toString()));

        Map<String, String> translations = city.getNameTranslationKey()
                .getTranslations()
                .stream()
                .collect(Collectors.toMap(Translation::getLanguageCode, Translation::getValue));

        String ru = translations.getOrDefault(LanguageCode.RU.toString(), "");
        String en = translations.getOrDefault(LanguageCode.EN.toString(), "");
        String resultString = String.format(
                "city_id: %s\ncode: %s\nru: %s\nen: %s\npopulation: %s",
                city.getId(),
                city.getCode(),
                ru,
                en,
                city.getPopulation()
        );

        DescriptionDto dto = new DescriptionDto();
        dto.setDescription(resultString);

        return dto;
    }

    @Transactional(readOnly = true)
    public Optional<CityDto> findById(Long id) {
        return cityRepository.findById(id).map(cityMapper::toDto);
    }

    @Transactional(readOnly = true)
    public List<CityDto> findAll() {
        return cityMapper.toDto(cityRepository.findAll());
    }

    @Transactional
    public void deleteCity(Long id) {
        cityRepository.deleteById(id);
    }
}
